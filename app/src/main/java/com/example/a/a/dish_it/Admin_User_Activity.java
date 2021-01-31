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

public class Admin_User_Activity extends AppCompatActivity {

    ArrayList<User> arrayList;
    // ArrayList<Dish> arrayListSearch;
    RecyclerView recyclerView;

    SqliteHelper database_helper;
    String userEmail;
    //  SearchView searchView;
    // CharSequence  searchWord;
    String Tag = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_recycle);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewAdmin);
        database_helper = new SqliteHelper(this);

        displayUser();

    }


    //display notes user
    public void displayUser() {
        arrayList = new ArrayList<>(database_helper.getAlluser());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        UserAdapter adapter = new UserAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);

    }



}
