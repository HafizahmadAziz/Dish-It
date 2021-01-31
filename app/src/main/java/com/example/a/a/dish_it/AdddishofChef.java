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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdddishofChef extends AppCompatActivity {

    ArrayList<Dish> arrayList;
    RecyclerView recyclerView;
    FloatingActionButton fabButton;
    SqliteHelper database_helper;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fabButton = (FloatingActionButton) findViewById(R.id.add);
        database_helper = new SqliteHelper(this);
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        displaydish();

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    //display notes dish
    public void displaydish() {
        arrayList = new ArrayList<>(database_helper.getAllAddeddishofChef(userEmail));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DishAdapter adapter = new DishAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    //display dialog
    public void showDialog() {
        final EditText dishName,dishDescription,dishPrice,dishChef;

        Button btnSave ;
        final String tag = "tag";
        final String Error = "Error";

        final Dialog dialog = new Dialog(AdddishofChef.this);
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

        dishName = (EditText)dialog.findViewById(R.id.txtDishName);
        dishDescription = (EditText)dialog.findViewById(R.id.txtDishDescription);
        dishPrice = (EditText)dialog.findViewById(R.id.txtPrice);
        dishChef = (EditText)dialog.findViewById(R.id.txtChefName);


        btnSave = (Button)dialog.findViewById(R.id.btnSaveDishDetails);



        btnSave.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                try {

                    if (dishName.getText().toString().isEmpty()) {

                        dishName.setError("Please enter valid Dish Name!");
                    }else if(dishName.getText().toString().length() >20) {
                        dishName.setError("Dish Name cann't be more than 20 char!");
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


                            Log.i(tag, "isDishnotExists then create" + dishName.getText().toString());
                            //Dish does not exist now add new user to database
                            database_helper.addDish(new Dish(null,
                                    dishName.getText().toString(), dishDescription.getText().toString(),
                                    dishPrice.getText().toString(),dishChef.getText().toString(),userEmail,null));

                            Toast.makeText(getApplicationContext(), "Dish added successfully", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            displaydish();

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
