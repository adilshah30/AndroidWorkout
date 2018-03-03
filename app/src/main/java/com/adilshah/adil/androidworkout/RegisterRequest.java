package com.adilshah.adil.androidworkout;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 28/01/2018.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://dev.adilshah.com/androidworkout/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String fullname , String username, String password, int age, Response.Listener listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener, null);
        params = new HashMap<>();

        params.put("fullname",fullname);
        params.put("username",username);
        params.put("password",password);
        params.put("age",age + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
