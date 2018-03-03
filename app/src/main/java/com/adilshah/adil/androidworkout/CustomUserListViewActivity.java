package com.adilshah.adil.androidworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomUserListViewActivity extends AppCompatActivity {

    private ListView listviewUser;
    private ArrayList<User> users_list;
    private ArrayAdapter<User> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_user_list_view);

        listviewUser = (ListView) findViewById(R.id.listviewUser);
        ///S
        setCustomUserListViewAdapter();



        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("User List", "there");
                try {
                    for (int i=0;i< response.length(); i++){

                        JSONObject jsonObject = response.getJSONObject(i);
                        User user = new User();
                        user.setFullname(jsonObject.getString("fullname"));
                        user.setUsername(jsonObject.getString("username"));
                        user.setProfile_img(jsonObject.getString("profile_img"));
                        user.setID(jsonObject.getInt("ID"));
                        //user.setPassword(jsonObject.getString("password"));
                        users_list.add(user);

                    }
                    Log.d("User List", String.valueOf(users_list));

                    userAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest(responseListener);
        RequestQueue requestQueue = Volley.newRequestQueue(CustomUserListViewActivity.this);
        requestQueue.add(getAllUsersRequest);

        listviewUser.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String item_name = String.valueOf(parent.getItemAtPosition(position));
                final String userId = (( TextView ) view.findViewById(R.id.tvUserID)).getText().toString();
                //Toast toastMsg = Toast.makeText(SimpleListviewUserActivity.this, "You Clicked User Id"+userId, Toast.LENGTH_LONG)
                TextView tvEditUser = ((TextView) view.findViewById( R.id.tvEditUser ) );
                TextView tvDeleteUser = ((TextView) view.findViewById( R.id.tvDeleteUser ) );

                tvEditUser.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent editUserIntent = new Intent( CustomUserListViewActivity.this, EditUserActivity.class );
                        editUserIntent.putExtra( "userID" , userId);
                        CustomUserListViewActivity.this.startActivity( editUserIntent );
                    }
                } );

                //Toast toastMsg = Toast.makeText(CustomUserListViewActivity.this, "You Clicked User Id"+userId, Toast.LENGTH_LONG);
                //toastMsg.show();
            }
        } );

    }

    private void setCustomUserListViewAdapter() {
        users_list = new ArrayList<User>();
        userAdapter = new CustomUserListAdapter(this, R.layout.custom_user_list_view_row, users_list);
        listviewUser.setAdapter(userAdapter);
    }


}
