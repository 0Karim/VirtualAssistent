package com.example.manobahnasy.virtualassistent;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Karim on 13/03/2018.
 */

public class Validation {

    //regular expression
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public boolean is_Empty(String data){
        if(TextUtils.isEmpty(data)){
           return true;
        }
        return false;
    }


    //on Regsteration Activity
    public String check_server_response_on_useername(String response){
        if(response.contains("username is found")){
            return "Change Username";
        }
        return "ok";
    }

    //on Regsteration Activity
    public String check_server_response_on_email(String response , String txt_email){
        if(response.contains("mail is found")){
            return "Change Email";
        }else if (!Pattern.matches(EMAIL_REGEX , txt_email)){
            return "Your Email Invalid";
        }
        return "ok";
    }

}
