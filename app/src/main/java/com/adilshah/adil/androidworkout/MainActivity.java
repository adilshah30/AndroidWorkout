package com.adilshah.adil.androidworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvLoginLink  = (TextView) findViewById(R.id.tvLoginLink);
        final TextView tvSimpleListviewUser =(TextView) findViewById(R.id.tvSimpleListviewUser);
        final TextView tvCustomListviewUser = (TextView) findViewById(R.id.tvCustomListviewUser);

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(loginIntent);

            }
        });

        tvSimpleListviewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simpleListviewUserIntent = new Intent(MainActivity.this,SimpleListviewUserActivity.class);
                MainActivity.this.startActivity(simpleListviewUserIntent);

            }
        });

        tvCustomListviewUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customUserListViewIntent = new Intent( MainActivity.this, CustomUserListViewActivity.class );
                MainActivity.this.startActivity( customUserListViewIntent );
            }
        } );




    }
}