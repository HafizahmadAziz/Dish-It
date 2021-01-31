package com.example.a.a.dish_it;

import android.app.Dialog;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Agent_Activity  extends AppCompatActivity {

    ArrayList<Delivery> arrayList;
    RecyclerView recyclerView;
    SqliteHelper sqliteHelper;
    String userEmail;
    String Tag = "tag";
    String Delivered ="false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewAgent);
        sqliteHelper = new SqliteHelper(this);
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        displayOrder();


    }


    //display notes Order
    public void displayOrder() {
        arrayList = new ArrayList<>(sqliteHelper.getAllOrderNotDeliverd(Delivered));

       if(arrayList.toString().isEmpty()) {
        Log.i(Tag,"Empty");}
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AgentAdapter adapter = new AgentAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);



    }

    //display dialog


}

