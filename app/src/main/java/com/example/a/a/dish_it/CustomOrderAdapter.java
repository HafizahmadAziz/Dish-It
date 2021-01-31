package com.example.a.a.dish_it;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomOrderAdapter extends RecyclerView.Adapter<CustomOrderAdapter.viewHolder> {

    private static String tag ="tag";
    Context context;
    Activity activity;
    ArrayList<Dish> arrayList;
    SqliteHelper database_helper;

    int Num = 0;
    String qty = "0";
    public static  HashMap<Integer,Integer>  hashMapQty = new HashMap<Integer, Integer>();

    public static  HashMap<Integer,Integer>  hashMapPrice = new HashMap<Integer, Integer>();;;

    public CustomOrderAdapter(Context context,Activity activity, ArrayList<Dish> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    public CustomOrderAdapter() {

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description,price,chef;
        EditText Number;
        CheckBox checkBox;




        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            price = (TextView)itemView.findViewById(R.id.price);
            chef = (TextView)itemView.findViewById(R.id.chef);
            Number= (EditText)itemView.findViewById(R.id.txtNumbers);
            checkBox= (CheckBox)itemView.findViewById(R.id.checkBox);

        }

    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_order, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {

        database_helper = new SqliteHelper(context);
        holder.title.setText(arrayList.get(position).getDishName());
        holder.description.setText(arrayList.get(position).getDishDescription());
        holder.price.setText(arrayList.get(position).getDishPrice() +" Rs ");
        holder.chef.setText(arrayList.get(position).getChef());

        holder.Number.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(tag," onTextChanged qty  "+position+"  " +Integer.parseInt(s.toString()));
                if(Integer.parseInt(s.toString())>0) {
                    hashMapQty.put(position, Integer.parseInt(s.toString()));
                    setHashMapQty(hashMapQty);
                    getHashMapQty();
                    Log.i(tag, " onTextChanged Integer.toString(position)  " + Integer.toString(position));
                    //  cars.add(Integer.toString(position));
                }else{
                    holder.Number.setError("Please Enter valid Quantity");
                }
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(tag,"Check box  :  ");
                if(isChecked ){
                       //&& Integer.parseInt(qty)>0
                    Log.i(tag,"Check box if ");


                    hashMapPrice.put(position,Integer.parseInt(arrayList.get(position).getDishPrice()));
                    setHashMapPrice(hashMapPrice);
                    getHashMapPrice();
                    Log.i(tag," onTextChanged qty  "+position+"  " +Integer.parseInt(arrayList.get(position).getDishPrice()));


                  //  Log.i(tag,"QTY  " +qty);
                  //  cars.add(arrayList.get(position).getDishPrice());
                    Log.i(tag,"arrayList.get(position).getDishPrice()  " +arrayList.get(position).getDishPrice());
                }else {
                    Log.i(tag,"Check box else ");
//                    cars.remove(Integer.toString(position));
//                    Log.i(tag,"Integer.toString(position)" +Integer.toString(position));
//
//                    cars.remove(qty);
//                    Log.i(tag,"QTY  " +qty);
//                    cars.remove(arrayList.get(position).getDishPrice());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        hashMapPrice.remove(position,Integer.parseInt(arrayList.get(position).getDishPrice()));
                        setHashMapPrice(hashMapPrice);
                        getHashMapPrice();
                    }
                    Log.i(tag,"arrayList.get(position).getDishPrice()  " +arrayList.get(position).getDishPrice());
                }

            }
        });




    }

    public  int TotalPriceCalculation (){
        ArrayList<String> total = new ArrayList<String>();
        int sum= 0;
        int value1 =0;
        int value2 =0;
        int add =0;
        Log.i(tag,"hashMapQty Values : "+ hashMapQty.isEmpty() );
        Log.i(tag,"hashMapPrice Values  : "+ hashMapPrice.isEmpty() );

        Log.i(tag,"hashMapQty Size : "+ hashMapQty.size() );
        Log.i(tag,"hashMapPrice Size  : "+ hashMapPrice.size() );
        final ArrayList<String> cars = new ArrayList<String>();
        for (int key : hashMapQty.keySet()) {
            value1 = hashMapQty.get(key);
            if(hashMapPrice.containsKey(key)) {
                value2 = hashMapPrice.get(key);

                Log.i(tag,"Values :  "+ value2 );
                sum = value1 * value2;
                Log.i(tag,"value1 :  "+ value1 );
                Log.i(tag,"value2 :  "+ value2 );
                Log.i(tag,"sum :  "+ sum );
                total.add(Integer.toString(sum));
            }
        }

        for (String str :total) {
             add =add +Integer.parseInt(str);
            Log.i(tag,"total: "+total+"Values :  "+ str );
            Log.i(tag,"total: "+total+"Values :  "+ add );
        }

        return add;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static HashMap<Integer, Integer> getHashMapQty() {
        Log.v(tag, "map2: E" + hashMapQty);
        return hashMapQty;
    }

    public void setHashMapQty(HashMap<Integer, Integer> hashMapQty) {
        this.hashMapQty = hashMapQty;
       getHashMapQty();
        Log.v(tag, "map1: E" + hashMapQty);
    }

    public static HashMap<Integer, Integer> getHashMapPrice() {
        Log.v(tag, "map2: E" + hashMapPrice);
        return hashMapPrice;
    }

    public void setHashMapPrice(HashMap<Integer, Integer> hashMapPrice) {
        this.hashMapPrice = hashMapPrice;
        getHashMapPrice();
        Log.v(tag, "map1: E" + hashMapPrice);
    }

}
