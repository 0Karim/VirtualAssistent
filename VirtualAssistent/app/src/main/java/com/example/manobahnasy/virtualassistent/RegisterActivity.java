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



public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mDisplayName , mEmail , mUsername , mPassword;
    private Button mCreateBtn;

    private FirebaseAuth mAuth;

    private Toolbar mToolbar;

    private String txt_Fullname , txt_Email , txt_Username , txt_Password;


    private Validation validate;

    private String server__url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        //Toolbar
        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Itiallize all Views
        intiallize_Views();

        //action Regestir Activity
        action_to_start_actvity();
    }

    //This Function to Refrence to The Views in the Register Activity
    //Fullname , Email , Username , Password and Buttons
    private void intiallize_Views(){
        mDisplayName = (TextInputLayout) findViewById(R.id.register_display_name);
        mEmail = (TextInputLayout) findViewById(R.id.register_email);
        mUsername = (TextInputLayout)findViewById(R.id.register_username);
        mPassword = (TextInputLayout) findViewById(R.id.register_password);

        mCreateBtn = (Button) findViewById(R.id.register_create_btn);

        validate = new Validation();
    }

    private void action_to_start_actvity(){
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_Fullname = getData(mDisplayName);
                if(validate.is_Empty(txt_Fullname)){
                    mDisplayName.getEditText().setError("Your Full Name is Required");
                    return;
                }

                txt_Email = getData(mEmail);
                if(validate.is_Empty(txt_Email)){
                    mEmail.getEditText().setError("Your Email is Required");
                    return;
                }

                txt_Username = getData(mUsername);
                if(validate.is_Empty(txt_Username)){
                    mUsername.getEditText().setError("Your Username is Required");
                    return;
                }

                txt_Password = getData(mPassword);
                if(validate.is_Empty(txt_Password)){
                    mPassword.getEditText().setError("Your Password is Required");
                    return;
                }

                server__url = "http://aya123fcih-001-site1.ctempurl.com/api/chat/register2?" +
                        "name=" + txt_Fullname +
                        "&mail=" + txt_Email +
                        "&username=" + txt_Username +
                        "&password=" + txt_Password +
                        "&alarmTime=1";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server__url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String check_username = validate.check_server_response_on_useername(response);
                                String check_email = validate.check_server_response_on_email(response , txt_Email);
                                if(!check_username.equals("ok")){
                                    mUsername.getEditText().setError(check_username);
                                    return;
                                }
                                if (!check_email.equals("ok")){
                                    mEmail.getEditText().setError(check_email);
                                    return;
                                }

                                //if true
                                Intent start_intent = new Intent(RegisterActivity.this , LoginActivity.class);
                                startActivity(start_intent);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this , "Error....." + error.getMessage() , Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        return;
                    }
                });

                //Make a Request
                MySingletone.getmInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }

    //this Function to return String from each EditText
    private String getData(TextInputLayout inputLayout){
        String Data = inputLayout.getEditText().getText().toString();
        return Data;
    }
}
