package com.example.a.a.dish_it;

import android.app.Activity;

import android.content.Context;

import android.os.Build;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AgentAdapter  extends RecyclerView.Adapter<AgentAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<Delivery> arrayList;
    SqliteHelper sqliteHelper;
    String tag = "tag";
    String valid = "true";
    public AgentAdapter(Context context,Activity activity, ArrayList<Delivery> arrayList ) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView totalPrice, Address,Contact;

       CheckBox checkBox;
        public viewHolder(View itemView) {
            super(itemView);
            totalPrice = (TextView)itemView.findViewById(R.id.totalPrice);
            Address = (TextView)itemView.findViewById(R.id.Address);
            Contact = (TextView)itemView.findViewById(R.id.custContact);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxDelivered);

        }
    }


    @Override
    public AgentAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_list, viewGroup, false);
        return new AgentAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AgentAdapter.viewHolder holder, final int position) {

        holder.totalPrice.setText("Total Price : "+arrayList.get(position).getTotalPrice());
        holder.Address.setText("Address : "+arrayList.get(position).getAddress());
        holder.Contact.setText("Contact No : "+arrayList.get(position).getContactNo());
        sqliteHelper = new SqliteHelper(context);


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(tag,"Check box  :  ");
                if(isChecked ){
                    //&& Integer.parseInt(qty)>0
                    Log.i(tag,"Check box if ");


                    sqliteHelper.updateOrder(arrayList.get(position).getId(),valid);

                    notifyDataSetChanged();
                    Log.i(tag," onTextChanged qty  "+position+"  " +arrayList.get(position).getId());


                }else {
                    Log.i(tag,"Check box else ");

                }

            }
        });





    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }










}
