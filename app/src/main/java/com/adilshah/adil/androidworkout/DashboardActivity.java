package com.adilshah.adil.androidworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername );
        final EditText etAge =  (EditText) findViewById(R.id.etAge );


        Intent get_login_intent = getIntent();

        String fullname= get_login_intent.getStringExtra("fullname");
        String username = get_login_intent.getStringExtra("username");
        int age = get_login_intent.getIntExtra("age",-1);

        String welcomeMessage = fullname + "Welcome to dashboard";
        tvWelcomeMsg.setText(welcomeMessage);
        etUsername.setText(username);
        etAge.setText(age +"");

    }
}
