package com.adilshah.adil.androidworkout;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditUserActivity extends AppCompatActivity {

    protected EditText etFullname;
    protected EditText etUsername;
    protected EditText etAge;
    protected Button btnUpdateUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_user2 );

        final String userID;

        //get Intent Values

        Intent get_custom_user_list_intent = getIntent();
        userID = get_custom_user_list_intent.getStringExtra( "userID" );
        Log.d("Intent Extra", userID);
        //Intialize text fields in activity

        etFullname = (EditText) findViewById( R.id.et_eu_Fullname );
        etUsername = (EditText) findViewById( R.id.et_eu_Username );
        etAge = (EditText) findViewById( R.id.et_eu_Age );
        btnUpdateUser = (Button) findViewById( R.id.btnUpdateUser );

        //Pre load text field with data
        loadUserData(userID);


        //

        btnUpdateUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String fullname = etFullname.getText().toString();
                final int age= Integer.parseInt(etAge.getText().toString());
                Log.d("Button Click", "Request Comes success");

                Response.Listener<String> responseListener =  new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            Boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent customuserlistviewIntent = new Intent(EditUserActivity.this,CustomUserListViewActivity.class);
                                EditUserActivity.this.startActivity(customuserlistviewIntent);
                                Log.d("Request", "Request Comes success");

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
                                builder.setMessage("Edit failed")
                                        .setNegativeButton("retry",null)
                                        .create()
                                        .show();
                                Log.d("Request", "Request Comes failed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                EditUserRequest editUserRequest = new EditUserRequest(fullname,username,age,userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(EditUserActivity.this);
                queue.add(editUserRequest);
            }
        } );

    }

    protected void loadUserData(String userID){

        Response.Listener<String> responseListener =  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.d("Comes", "hiting api");
                    JSONObject jsonObject = new JSONObject( response );
                    Boolean success_msg = jsonObject.getBoolean( "success" );

                    if(success_msg){
                        String get_username = jsonObject.getString( "username" );
                        String get_fullname =  jsonObject.getString( "fullname" );
                        String get_age = jsonObject.getString( "age" );

                        etFullname.setText( get_fullname );
                        etUsername.setText( get_username );
                        etAge.setText( get_age+"" );
                        Log.d("get user", "Request Comes success");
                        Log.d("success response", get_username);

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
                        builder.setMessage("Failed to load user")
                                .setNegativeButton("retry",null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        GetUserRequest getUserRequest = new GetUserRequest( userID , responseListener  );
        RequestQueue requestQueue = Volley.newRequestQueue( EditUserActivity.this );
        requestQueue.add( getUserRequest );

    }
}
