package com.example.a.a.dish_it;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cart_Orders extends AppCompatActivity {

    EditText Address,ContactNo;
    TextView TotalValue;
    Button Submit;
    String tag ="tag";
    String Error = "Error";
    String  totalPrice ="";
    String userEmail;
    SqliteHelper sqliteHelper;
    String Deliver ="false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_order);

        sqliteHelper = new SqliteHelper(this);
        Intent intent = getIntent();
        totalPrice =intent.getStringExtra("TotalValue");
        userEmail =intent.getStringExtra("UserEmail");
        Address = (EditText)findViewById(R.id.txtAddress);
        ContactNo = (EditText)findViewById(R.id.contactNo);


        TotalValue= (TextView)findViewById(R.id.TotalPrice);

        TotalValue.setText("Total Price : "+totalPrice);



        Submit = (Button)findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                Log.i(tag, "Submit Inside " + TotalValue);
                    Log.i(tag, "Submit Inside " + userEmail);
                if (Address.getText().toString().isEmpty()) {

                    ContactNo.setError("Please Enter Valid Address!");
                    Log.i(tag, "Please Enter Valid Address " + Address.getText().toString());

                }else if( ContactNo.getText().toString().isEmpty()){
                    ContactNo.setError("Please Enter Contact No!");
                    Log.i(tag, "Please Enter Contact No " + ContactNo.getText().toString());
                }else if((ContactNo.getText().toString().length()>0) && (ContactNo.getText().toString().length()<11)){

                    ContactNo.setError("Please Enter Valid Contact No!");
                    Log.i(tag, "Contact No must be 11 digit " + ContactNo.getText().toString().length());
                }else{
                    Log.i(tag, "Moving to CustomOrderActivity activity " );
                    sqliteHelper.addOrder( new Delivery(null,userEmail,totalPrice,Address.getText().toString(),
                            ContactNo.getText().toString(),Deliver));
                    Intent i=new Intent(Cart_Orders.this, CustomOrderActivity.class);

                    startActivity(i);

                    finish();
                }
                }catch(Exception ex){
                    Log.i(tag, "Cart order Activity Submit button Exception " +ex);
                }


            }
        });





    }
}