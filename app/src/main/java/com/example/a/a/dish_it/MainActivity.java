package com.example.a.a.dish_it;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String tag = "tag";
    String Error = "Error";
    String Customer="Customer";
    String Chef = "Chef";
    String DeliveryAgent ="Delivery Agent";
    String Admin ="Admin";
    EditText EmailSignIn;
    EditText passwordSignIn;
    TextView lnkSignUp;
    Button SignIn;
    TextView lnkForgetPwd;
    SqliteHelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);

        setupUI();
        setupListeners();
    }

    private void setupUI() {
        try {
            EmailSignIn = findViewById(R.id.txtEmailSignIn);
            passwordSignIn = findViewById(R.id.txtPwdSignIn);
            lnkSignUp = findViewById(R.id.lnkSignUp);
            SignIn = findViewById(R.id.btnSignIn);
            lnkForgetPwd = findViewById(R.id.lnkForgetPwd);

        }catch (Exception ex) {

            Log.e(Error, "setupUI inside "+ ex);
        }
    }

    private void setupListeners() {
        try {


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  checkUsername();
                //Check user input is correct or not
                if (validate()) {

                    Log.i(tag, "Validate inside " + validate());


                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, EmailSignIn.getText().toString(),
                            passwordSignIn.getText().toString(), null));


                    Log.i(tag, "currentUser Authentication  " + EmailSignIn.getText().toString());
                    String currentUserType = sqliteHelper.AuthenticateUserType(EmailSignIn.getText().toString());


                    Log.i(tag, "currentUser Authentication response " + currentUser);
                    Log.i(tag, "currentUserType Authentication response " + currentUserType);
                    //Check Authentication is successful or not
                    if (currentUser != null) {

                        Log.i(tag, "currentUser inside " + currentUser);
                        Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        //User Logged in Successfully Launch You home screen activity

                        if (currentUserType.equals(Chef)) {
                            Log.i(tag, "Moving Chef activity   " + currentUserType);
                            Intent i = new Intent(MainActivity.this, AdddishofChef.class);
                            i.putExtra("userEmail", EmailSignIn.getText().toString());
                            startActivity(i);
                            //we close this activity
                            finish();
                        } else if (currentUserType.equals(Customer)) {
                            Log.i(tag, "Moving Customer activity   " + currentUserType);
                            Intent i = new Intent(MainActivity.this, CustomOrderActivity.class);
                            i.putExtra("userEmail", EmailSignIn.getText().toString());
                            startActivity(i);
                            //we close this activity
                            finish();
                        } else if (currentUserType.equals(DeliveryAgent)) {
                            Log.i(tag, "Moving Agent_Activity activity   " + currentUserType);
                            Intent i = new Intent(MainActivity.this, Agent_Activity.class);
                            i.putExtra("userEmail", EmailSignIn.getText().toString());
                            startActivity(i);

                            finish();
                        }
                        else if (currentUserType.equals(Admin)) {
                            Log.i(tag, "Moving Admin activity   " + currentUserType);
                            Intent i = new Intent(MainActivity.this, Admin_Account.class);
                            i.putExtra("userEmail", EmailSignIn.getText().toString());
                            startActivity(i);
                            finish();

                        }
                    } else{
                        EmailSignIn.setError("Enter the correct email!");
                        passwordSignIn.setError("Enter the correct password!");
                    }


                    }
                else{

                        //User Logged in Failed
                         Toast.makeText(getApplicationContext(), "Failed to log in , please try again", Toast.LENGTH_SHORT).show();
                    }
                }

        });
        }catch (Exception ex) {

            Log.e(Error, "SignIn inside "+ ex);
        }
        try {


        lnkSignUp.setOnClickListener(new View.OnClickListener() {

            String tag = "SignUp";
            public void onClick(View view) {
                try {
                    Log.e(tag, "Progress here ");
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
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
                    Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                    startActivity(i);

                        finish();

                }catch (Exception ex){
                    Log.e(tag, ex.toString());
                }

            }
        });
        }catch (Exception ex) {

     Log.i(tag, "lnkSignUp inside "+ ex);
        }

        lnkForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        final EditText forgetEmail,forgetPwd;

        Button btnSubmitpassword ;
        final String tag = "tag";
        final String Error = "Error";

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.activity_forget_pwd);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        forgetEmail = (EditText)dialog.findViewById(R.id.txtEmail);
        forgetPwd = (EditText)dialog.findViewById(R.id.txtForgetPwd);



        btnSubmitpassword = (Button)dialog.findViewById(R.id.btnForgetPwdSubmit);



        btnSubmitpassword.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                try {
                    Log.i(tag, "Password length" + forgetPwd.getText().toString().length());
                    if (forgetEmail.getText().toString().isEmpty()) {

                        forgetEmail.setError("Please enter valid Email!");
                    }else if (forgetPwd.getText().toString().isEmpty()) {

                        forgetPwd.setError("Please enter Password!");
                    }else if (forgetPwd.getText().toString().length() < 5) {
                        forgetPwd.setError("Password must be greater than 5 char!");

                    } else{
                        Log.i(tag, "btn Save else Pwd updation Inside"  );

                        if (sqliteHelper.isEmailExists(forgetEmail.getText().toString())) {


                            Log.i(tag, "isDishnotExists then " + forgetEmail.getText().toString());
                            //Dish does not exist now add new user to database
                            //updating dish
                            sqliteHelper.updatePwd( forgetEmail.getText().toString(),
                                    forgetPwd.getText().toString());

                            Toast.makeText(getApplicationContext(), "Password reset successfully", Toast.LENGTH_SHORT).show();
                            dialog.cancel();


                        } else {
                            forgetEmail.setError("Please Enter the correct email");
                            Log.i(tag, "Please Enter the correct email --Pwd updation");

                        }
                    }
                }catch (Exception ex) {

                    Log.e(Error, "btnSave inside "+ ex);
                }


            }
        });
    }



    //This method is used to validate input given by user
    public boolean validate() {
        boolean isValid = true;
        try {



        if (isEmpty(EmailSignIn)) {
            Log.i(tag, "Email Signup " + isEmpty(EmailSignIn));
            EmailSignIn.setError("You must enter Email to SignIn!");
            isValid = false;
        }else {
            if (!isEmail(EmailSignIn)) {
                EmailSignIn.setError("Enter a valid Email!");
                isValid = false;
            }

        }
        if (isEmpty(passwordSignIn)) {
            passwordSignIn.setError("You must enter password to SignIn!");
            isValid = false;
        } else {
            if (passwordSignIn.getText().toString().length() < 4) {
                passwordSignIn.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }
        }catch (Exception ex) {

            Log.e(Error, "validate inside "+ ex);
        }

        return isValid;
    }


   boolean isEmail(EditText text) {
       CharSequence email = text.getText().toString();
       try {

        Log.i(tag,"Email checking"+Patterns.EMAIL_ADDRESS.matcher(email).matches());
       }catch (Exception ex) {

           Log.e(Error, "validate inside "+ ex);
       }

       return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }




}
