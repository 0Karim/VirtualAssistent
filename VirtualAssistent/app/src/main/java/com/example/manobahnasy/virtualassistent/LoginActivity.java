package com.example.manobahnasy.virtualassistent;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mLoginUsername;
    private TextInputLayout mLoginPassword;
    private Button mLogin_btn;

    private Toolbar mToolbar;

    StringRequest stringRequest;
    String server_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Toolbar
        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //this function to intallize all views in Login Activity
        Intiallize_Views();

        //Login Button Action
        action_of_Login();
    }


    private void Intiallize_Views(){
        mLoginUsername = (TextInputLayout) findViewById(R.id.login_username);
        mLoginPassword = (TextInputLayout) findViewById(R.id.login_password);
        mLogin_btn = (Button) findViewById(R.id.login_btn);
    }

    private void action_of_Login(){

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_Username = getData(mLoginUsername);
                String txt_Password = getData(mLoginPassword);

                if(new Validation().is_Empty(txt_Username)){
                   mLoginUsername.getEditText().setError("Your Username is Required to Login");
                   return;
                }
                if(new Validation().is_Empty(txt_Password)){
                    mLoginPassword.getEditText().setError("Your Password is Required to Login");
                    return;
                }

                //API HERE
                server_url = "http://aya123fcih-001-site1.ctempurl.com/api/chat/login2?" +
                        "username=" + txt_Username +
                        "&password=" + txt_Password;


                stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (!response.contains("user is not found")){
                                    Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(loginIntent);
                                    finish();
                                }else{
                                    mLoginUsername.getEditText().setError("Error in Username or Not Found");
                                    mLoginPassword.getEditText().setError("Error in Password");
                                    return;
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this , "Error....." + error.getMessage() , Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        return;
                    }
                });

                MySingletone.getmInstance(LoginActivity.this).addToRequestQueue(stringRequest);
            }
        });

    }

    private String getData(TextInputLayout field){
        String Data = field.getEditText().getText().toString().trim();
        return Data;
    }
}
