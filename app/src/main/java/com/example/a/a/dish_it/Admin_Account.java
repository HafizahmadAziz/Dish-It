package com.example.a.a.dish_it;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;




public class Admin_Account extends AppCompatActivity {

    Button userList, menuList;
    TextView userName;
    String tag = "tag";
    String userEmail;
    SqliteHelper sqliteHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        userList = (Button)findViewById(R.id.btnUserList);
        menuList =(Button)findViewById(R.id.btnMenuList);
        userName = (TextView)findViewById(R.id.username);
        sqliteHelper = new SqliteHelper(this);



        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");
        userName.setText(sqliteHelper.getUserName(userEmail));
        userList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(tag, "Moving Admin activity   " + userEmail);
                Intent i = new Intent(Admin_Account.this, Admin_User_Activity.class);

                startActivity(i);

            }
        });

        menuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(tag, "Moving Admin activity   " + userEmail);
                Intent i = new Intent(Admin_Account.this, HomeActivity.class);
                i.putExtra("userEmail", userEmail);
                startActivity(i);
            }
        });

    }

}
