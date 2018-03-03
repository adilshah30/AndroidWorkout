package com.adilshah.adil.androidworkout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 11/02/2018.
 */

public class GetAllUsersRequest extends JsonArrayRequest {

    private static final String All_USERS_REQUEST_URL = "http://dev.adilshah.com/androidworkout/Users.php";
    private Map<String, String> params;

    public GetAllUsersRequest(Response.Listener listener){
        super(Method.GET, All_USERS_REQUEST_URL, null, listener,null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
