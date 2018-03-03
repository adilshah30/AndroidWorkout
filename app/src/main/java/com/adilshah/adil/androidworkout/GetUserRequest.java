package com.adilshah.adil.androidworkout;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 20/02/2018.
 */

public class GetUserRequest extends StringRequest {

    private static final String GET_USER_URL = "http://dev.adilshah.com/androidworkout/Getuser.php";
    private Map<String, String> params;

    public GetUserRequest(String userID, Response.Listener listener){
        super(Method.POST,GET_USER_URL,listener, null);
        params = new HashMap<>();
        params.put("userID",userID + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
