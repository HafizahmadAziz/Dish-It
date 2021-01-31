package com.example.a.a.dish_it;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomOrderActivity extends AppCompatActivity {

    ArrayList<Dish> arrayList;
    RecyclerView recyclerView;
    Button button;


    SqliteHelper database_helper;
    String tag ="tag";
    String userEmail;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_order_recycleview);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewCustom);
        database_helper = new SqliteHelper(this);
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");


//       final HashMap<Integer, Integer> hashMapQty =new HashMap<Integer, Integer>(CustomOrderAdapter.getHashMapQty());
//       final HashMap<Integer, Integer> hashMapPrice =new HashMap<Integer, Integer>(CustomOrderAdapter.getHashMapPrice());
//

           displaydish();

    }

    //display notes dish
    public void displaydish() {
        arrayList = new ArrayList<>(database_helper.getAllData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CustomOrderAdapter adapter = new CustomOrderAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            Log.i(tag," action_cart     " +id);
          CustomOrderAdapter customOrderAdapter = new CustomOrderAdapter();

         int value = customOrderAdapter.TotalPriceCalculation();
         if(value>0) {
             Log.i(tag, " action_cart   2  ");
             Intent i=new Intent(CustomOrderActivity.this, Cart_Orders.class);
             i.putExtra("TotalValue", Integer.toString(value));
             i.putExtra("UserEmail", userEmail);
             startActivity(i);
             finish();
         }else{
             Toast.makeText(getApplicationContext(),"please select Add to cart check and enter valid Quantity",Toast.LENGTH_LONG).show();
         }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }





}
