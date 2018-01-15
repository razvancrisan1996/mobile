package com.example.razvan.fitness;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsersList extends AppCompatActivity {

    Intent intent;
    String email,age,weight;
    UsersListAdapter adapter;
    ArrayList<User> usersL;
    SwipeMenuListView userListView;
    String delete = "http://www.caloriesintake.ga/Delete.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        intent = getIntent();

        userListView = (SwipeMenuListView) findViewById(R.id.listUsersView);
        usersL = new ArrayList<User>();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONArray users = response.getJSONArray("users");
                    for (int i=0;i<users.length();i++){
                        JSONObject user = users.getJSONObject(i);
                        String user_id = user.getString("user_id");
                        String email = user.getString("email");
                        String age = user.getString("age");
                        String weight = user.getString("weight");

                        User user1 = new User(Integer.valueOf(user_id),email,Integer.valueOf(age),Integer.valueOf(weight));
                        usersL.add(user1);
                    }
                    adapter = new UsersListAdapter(UsersList.this,R.layout.adapter_view_layout,usersL);
                    userListView.setAdapter(adapter);
                    SwipeMenuCreator creator = new SwipeMenuCreator() {
                        @Override
                        public void create(SwipeMenu menu) {
                            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xf7, 0x09, 0x09)));
                            deleteItem.setIcon(R.drawable.ic_delete);
                            deleteItem.setWidth(170);
                            menu.addMenuItem(deleteItem);

                        }
                    };
                    userListView.setMenuCreator(creator);
                    userListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                            final String user_id = String.valueOf(adapter.getItem(position).getId());

                            switch (index) {
                                case 0:
                                    StringRequest request = new StringRequest(Request.Method.POST,
                                            delete, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }){
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String,String> parameters = new HashMap<String, String>();
                                            parameters.put("user_id",user_id);
                                            return parameters;
                                        }
                                    };
                                    adapter.remove(adapter.getItem(position));
                                    userListView.setAdapter(adapter);
                                    requestQueue.add(request);

                                    break;
                            }
                            return false;
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ReadRequest readRequest = new ReadRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(UsersList.this);
        queue.add(readRequest);



    }
}
