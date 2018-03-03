package com.adilshah.adil.androidworkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SimpleListviewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_listview_user);



        final ListView listviewUsers = (ListView) findViewById(R.id.textviewUsers);
        final ArrayList<String> users_list = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users_list);
        listviewUsers.setAdapter(arrayAdapter);

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    //JSONObject jsonResponse = new JSONObject(response);
                    //String fullname = jsonResponse.getString("fullname");
                    //Log.d("Response", jsonResponse.toString());
                    //Log.d("Request", "Request Comes success");
                    for (int i=0; i<response.length(); i++){

                        JSONObject jsonUserObject = response.getJSONObject(i);
                        String fullname = jsonUserObject.getString("fullname");
                        String username = jsonUserObject.getString("username");
                        users_list.add(fullname);
                        users_list.add(username);
                        Log.d("json Name", fullname);

                    }
                    Log.d("User List", String.valueOf(users_list));
                    arrayAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Error.Response", "error json");
                }
            }
        };

        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest(responseListener);
        RequestQueue requestQueue = Volley.newRequestQueue(SimpleListviewUserActivity.this);
        requestQueue.add(getAllUsersRequest);
        Log.d("Error.Response", String.valueOf(getAllUsersRequest));


        listviewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item_name = String.valueOf(parent.getItemAtPosition(position));
                Toast toastMsg = Toast.makeText(SimpleListviewUserActivity.this, "You Clicked "+item_name, Toast.LENGTH_LONG);
                toastMsg.show();
            }
        });

    }


}
