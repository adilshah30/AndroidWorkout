package com.adilshah.adil.androidworkout;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 28/01/2018.
 */

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://dev.adilshah.com/androidworkout/Login.php";
    private Map<String, String> params;

    public LoginRequest( String username, String password, Response.Listener listener){
        super(Method.POST,LOGIN_REQUEST_URL,listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
