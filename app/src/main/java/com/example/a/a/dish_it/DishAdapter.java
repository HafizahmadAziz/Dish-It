package com.example.a.a.dish_it;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<Dish> arrayList;
    SqliteHelper database_helper;
    String tag = "tag";
    public DishAdapter(Context context,Activity activity, ArrayList<Dish> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description,price,chef;

        ImageView delete, edit;
        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            price = (TextView)itemView.findViewById(R.id.price);
            chef = (TextView)itemView.findViewById(R.id.chef);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            edit = (ImageView) itemView.findViewById(R.id.edit);

        }
    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dish_list, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {

        holder.title.setText("Dish Name : "+arrayList.get(position).getDishName());
        holder.description.setText("Description : "+arrayList.get(position).getDishDescription());
        holder.price.setText("Price : "+arrayList.get(position).getDishPrice());
        holder.chef.setText("Chef : "+arrayList.get(position).getChef());
        database_helper = new SqliteHelper(context);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            ;

            @Override
            public void onClick(View v) {
                //deleting note
                database_helper.deleteDish(arrayList.get(position).getDishId());
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //display edit dialog
                showDialog(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public void showDialog(final int pos) {
        final EditText dishName,dishDescription,dishPrice,dishChef;

        Button btnSave ;
        final String tag = "tag";
        final String Error = "Error";

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.activity_dish_details);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        dishName = (EditText) dialog.findViewById(R.id.txtDishName);
        dishDescription = (EditText) dialog.findViewById(R.id.txtDishDescription);
        dishPrice = (EditText) dialog.findViewById(R.id.txtPrice);
        dishChef = (EditText) dialog.findViewById(R.id.txtChefName);


        btnSave = (Button) dialog.findViewById(R.id.btnSaveDishDetails);

        dishName.setText(arrayList.get(pos).getDishName());
        dishDescription.setText(arrayList.get(pos).getDishDescription());
        dishPrice.setText(arrayList.get(pos).getDishPrice());
        dishChef.setText(arrayList.get(pos).getChef());

        btnSave.setOnClickListener(new View.OnClickListener() {;




            @Override
            public void onClick(View v) {
                try {

                    if (dishName.getText().toString().isEmpty()) {

                        dishName.setError("Please enter valid Dish Name!");
                    }else if (dishDescription.getText().toString().isEmpty()) {

                        dishDescription.setError("Please enter Description!");
                    }else if (dishPrice.getText().toString().isEmpty()) {

                        dishPrice.setError("Please enter Price!");
                    }else if (dishChef.getText().toString().isEmpty()) {

                        dishChef.setError("Please enter Chef name!");
                    }
                    else{
                        Log.i(tag, "btn Save else dish updation Inside"  );

                            if (!database_helper.isDishExists(dishName.getText().toString())) {

                                Log.i(tag, "isDishnotExists then update" + dishName.getText().toString());

                                //updating dish
                                database_helper.updateDish(arrayList.get(pos).getDishId(), dishName.getText().toString(),
                                        dishDescription.getText().toString(), dishPrice.getText().toString(),
                                        dishChef.getText().toString()
                                );
                                Log.i(tag, "isDishnotExists then updated dish" + dishName.getText().toString());
                                arrayList.get(pos).setDishName(dishName.getText().toString());
                                arrayList.get(pos).setDishDescription(dishDescription.getText().toString());
                                arrayList.get(pos).setDishPrice(dishPrice.getText().toString());
                                arrayList.get(pos).setChef(dishChef.getText().toString());
                                dialog.cancel();

                                Log.i(tag, "isDishnotExists then dialog.fish" + dishName.getText().toString());
                                //notify list
                                notifyDataSetChanged();
                            } else {
                                dishName.setError("Dish exist with same Name");
                                Log.i(tag, "Dish exists with dishId input provided so show error user already exist");

                            }
                        }
                }catch (Exception ex) {

                    Log.e(Error, "btnSave inside "+ ex);
                }


            }
        });
    }






}
