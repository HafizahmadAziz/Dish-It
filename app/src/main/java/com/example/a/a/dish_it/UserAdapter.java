package com.example.a.a.dish_it;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Transliterator;
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

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<User> arrayList;
    SqliteHelper database_helper;
    String tag = "tag";


    public UserAdapter(Context context,Activity activity, ArrayList<User> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView userName, Email,Password;

        ImageView delete, edit;
        public viewHolder(View itemView) {
            super(itemView);
            userName = (TextView)itemView.findViewById(R.id.AdminuserName);
            Email = (TextView)itemView.findViewById(R.id.txtUseremail);
            Password = (TextView)itemView.findViewById(R.id.txtUserPassword);


            delete = (ImageView) itemView.findViewById(R.id.imgDelete);
            edit = (ImageView) itemView.findViewById(R.id.imgEdit);

        }
    }


    @Override
    public UserAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list, viewGroup, false);
        return new UserAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserAdapter.viewHolder holder, final int position) {




        holder.userName.setText("User Name : "+arrayList.get(position).getUserName());
        holder.Email.setText("Email : "+arrayList.get(position).getEmail());
        holder.Password.setText("Password : "+arrayList.get(position).getPassword());
        Log.i(tag,"Password checking "+arrayList.get(position).getPassword());

        database_helper = new SqliteHelper(context);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            ;

            @Override
            public void onClick(View v) {
                //deleting note
                database_helper.deleteUser(arrayList.get(position).getId());
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



    public void showDialog(final  int position) {
        final EditText UserName,Email,Password;

        Button btnSave ;
        final String tag = "tag";
        final String Error = "Error";

         final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.user_details);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        UserName = (EditText)dialog.findViewById(R.id.UserName);
        Email = (EditText)dialog.findViewById(R.id.Email);
        Password = (EditText)dialog.findViewById(R.id.Password);
        UserName.setText(arrayList.get(position).getUserName());
        Email.setText(arrayList.get(position).getEmail());
        Password.setText(arrayList.get(position).getPassword());




        btnSave = (Button)dialog.findViewById(R.id.btnSaveUser);




        btnSave.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                try {

                    if (UserName.getText().toString().isEmpty()) {

                        UserName.setError("Please enter valid v!");
                    }else if (Email.getText().toString().isEmpty()) {

                        Email.setError("Please enter Email!");
                    }else if (Password.getText().toString().isEmpty()) {

                        Password.setError("Please enter Password!");
                    }
                    else{
                        Log.i(tag, "btn Save else dish updation Inside"  );

                        if (database_helper.isEmailExists(Email.getText().toString())) {


                            Log.i(tag, "isEmailnotExists then create" + Email.getText().toString());
                            //Dish does not exist now add new user to database
                            database_helper.updateUserDetail(arrayList.get(position).getId(),
                                    UserName.getText().toString(), Email.getText().toString(),
                                    Password.getText().toString());

                          //  Toast.makeText(Admin_User_Activity.this, "User Detail added successfully", Toast.LENGTH_SHORT).show();
                            arrayList.get(position).setUserName(UserName.getText().toString());
                            arrayList.get(position).setEmail(Email.getText().toString());
                            arrayList.get(position).setPassword(Password.getText().toString());

                            dialog.cancel();
                            notifyDataSetChanged();

                        } else {
                            Email.setError("Email exist with same Name");
                            Log.i(tag, "Email exists with Id input provided so show error user already exist");

                        }
                    }
                }catch (Exception ex) {

                    Log.e(Error, "btnSave inside "+ ex);
                }


            }
        });
    }






}
