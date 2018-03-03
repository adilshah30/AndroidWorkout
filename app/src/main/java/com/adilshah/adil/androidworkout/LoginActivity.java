package com.adilshah.adil.androidworkout;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername );
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin= (Button) findViewById(R.id.btnLogin);
        final TextView tvRegisterHereLink = findViewById(R.id.tvRegisterHereLink);

        tvRegisterHereLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse =  new JSONObject(response);
                            Boolean success = jsonResponse.getBoolean("success");

                            if (success){

                                String fullname = jsonResponse.getString("fullname");
                                int age = jsonResponse.getInt("age");

                                Intent dashboardIntent = new Intent(LoginActivity.this,DashboardActivity.class);
                                dashboardIntent.putExtra("fullname", fullname );
                                dashboardIntent.putExtra("username", username);
                                dashboardIntent.putExtra("age", age);

                                LoginActivity.this.startActivity(dashboardIntent);


                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,password,responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });

    }
}
