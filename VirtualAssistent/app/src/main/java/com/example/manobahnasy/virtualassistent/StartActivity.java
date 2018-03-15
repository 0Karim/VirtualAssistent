package com.example.manobahnasy.virtualassistent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {


    Button register_Btn , login_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Set Refrence to Buttons
        intiallize_Buttons();

        //this action to go the login activity
        action_to_Login_Activity();

        //this action go to the register activity
        action_to_Register_Activity();

    }



    //this Function make a refrence to the 2 Buttons in the MainActivity Views
    //Already have account => Login Activity
    //Need An Account => Register Activity
    private void intiallize_Buttons(){
        login_Btn = (Button)findViewById(R.id.start_login_btn);
        register_Btn = (Button)findViewById(R.id.start_register_btn);
    }

    //this
    private void action_to_Register_Activity(){
        register_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Craete new Intent to The Register Activity
                Intent register_Intent = new Intent(StartActivity.this , RegisterActivity.class);
                startActivity(register_Intent);
                //to not back to the start activity from the back button in the device
                finish();
            }
        });
    }

    private void action_to_Login_Activity(){
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create new intnet to go to the Login Activity
                Intent login_Intent = new Intent(StartActivity.this , LoginActivity.class);
                startActivity(login_Intent);
                //to not back to the start activity from the back button in the device
                finish();
            }
        });
    }
}
