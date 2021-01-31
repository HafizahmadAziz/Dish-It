package com.example.a.a.dish_it;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.a.a.dish_it.R.array.userType_spinner;

public class RegistrationActivity extends AppCompatActivity {

    SqliteHelper sqliteHelper;
    String tag = "tag";
    EditText userName;
    EditText email;
    EditText pwd;
    Button signUp;
    TextView lnkSignIn;
    String Error = "Error";
    ProgressDialog progressDialog;
    Spinner userType;
    String selecteduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        sqliteHelper = new SqliteHelper(this);
        userName = findViewById(R.id.txtUserName);
        email = findViewById(R.id.txtEmail);
        pwd = findViewById(R.id.txtPwd);
        signUp = findViewById(R.id.btnSignUp);
        lnkSignIn =findViewById(R.id.lnkSignIn);
        userType = (Spinner)findViewById(R.id.userType_spinner);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(RegistrationActivity.this,
                android.R.layout.simple_spinner_item,getResources().getStringArray(userType_spinner));
       /* spinner.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(userType_spinner)));*/

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);
        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 selecteduser = parent.getItemAtPosition(position).toString();

                Log.i(tag, "onItemSelected Inside" +selecteduser);

               // Toast.makeText(RegistrationActivity.this, "\n Class: \t " + selecteduser , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                Log.i(tag, "You must select one option");

                ((TextView)userType.getSelectedView()).setError("You must select Account Type ");
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Log.i(tag, "Validation Inside" + validate());

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(email.getText().toString())) {

                        Log.i(tag, "isEmailnotExists then create" + email.getText().toString());
                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, userName.getText().toString(),
                                email.getText().toString(), pwd.getText().toString(), selecteduser));

                        Toast.makeText(getApplicationContext(), "User created successfully! Please Login ", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Toast.LENGTH_LONG);
                        Log.i(tag, "After account creation ");
                        Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        //User Logged in Successfully Launch You home screen activity
                        progressDialog = new ProgressDialog(RegistrationActivity.this);
                        progressDialog.setMessage("Please wait ..."); // Setting Message
                        progressDialog.setTitle("Loading"); // Setting Title
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                        progressDialog.show(); // Display Progress Dialog
                        progressDialog.setCancelable(false);
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(100000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            }
                        }).start();


                        Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(i);
                        //we close this activity
                        finish();
                    } else {

                        //Email exists with email input provided so show error user already exist
                        Log.i(tag, "Email exists with email input provided so show error user already exist");
                        email.setError("User already exists with same email");
                        Toast.makeText(getApplicationContext(), "User already exists with same email ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        lnkSignIn.setOnClickListener(new View.OnClickListener() {

            String tag = "SignIn";
            public void onClick(View view) {
                try {
                    Log.e(tag, "Progress here ");
                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setMessage("Please wait ..."); // Setting Message
                    progressDialog.setTitle("Loading"); // Setting Title
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(100000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();

                    Log.i(tag, "Progress here after");
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();



                }catch (Exception ex){
                    Log.e(tag, ex.toString());
                }

            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    /*void checkDataEntered() {
        try {
            if (isEmpty(userName)) {
                Toast t = Toast.makeText(this, "You must enter User name to register!", Toast.LENGTH_SHORT);
                t.show();
            }

            if (isEmpty(pwd)) {
                pwd.setError("Last name is required!");
            }

            if (isEmail(email) == false) {
                email.setError("Enter valid email!");
            }
        }catch (Exception ex){
            Log.e(Error,ex.toString() );
        }


    }*/

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = userName.getText().toString();
        String Email = email.getText().toString();
        String Password = pwd.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            userName.setError("Please enter valid username!");
        }
        /*else {
            if (UserName.length() >= 5) {
                valid = true;

            } else {
                valid = false;
                userName.setError("Username is to short!");
            }
        }*/

        //Handling validation for Email field
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            email.setError("Please enter valid email!");
        } else {
            valid = true;

        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            pwd.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;

            } else {
                valid = false;
                pwd.setError("Password is to short!");
            }
        }

        Log.i(tag,"spin");
          int selectedItemOfMySpinner = userType.getSelectedItemPosition();
        String actualPositionOfMySpinner = (String) userType.getItemAtPosition(selectedItemOfMySpinner);
        Log.i(tag,"spin"+ actualPositionOfMySpinner);
        if (actualPositionOfMySpinner.equals("Select one")){
            Log.i(tag, "You must select one option");
            valid = false;
            ((TextView)userType.getSelectedView()).setError("You must select Account Type ");
        }


        return valid;
    }

}
