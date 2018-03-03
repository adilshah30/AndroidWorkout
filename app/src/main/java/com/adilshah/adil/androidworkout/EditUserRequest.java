package com.adilshah.adil.androidworkout;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 21/02/2018.
 */

public class EditUserRequest extends StringRequest {

    private static final String EDIT_USER_URL = "http://dev.adilshah.com/androidworkout/EditUserRequest.php";
    private Map<String, String> params;

    public EditUserRequest(String fullname , String username, int age,String userID ,Response.Listener listener) {
        super( Method.POST,EDIT_USER_URL,listener, null );

        params = new HashMap<>(  );
        params.put( "username",username );
        params.put( "fullname", fullname );
        params.put( "age",age +"" );
        params.put( "userID",userID );
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
