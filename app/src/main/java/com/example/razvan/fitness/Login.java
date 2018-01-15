package com.example.razvan.fitness;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.btLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegister);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String user_id = jsonResponse.getString("user_id");
                                String weight = jsonResponse.getString("weight");
                                String age = jsonResponse.getString("age");
                                String genre = jsonResponse.getString("genre");
                                String utype = jsonResponse.getString("utype");

                                Intent intent = new Intent(Login.this, MainActivity.class);

                                intent.putExtra("email",email);
                                intent.putExtra("user_id",user_id);
                                intent.putExtra("weight",weight);
                                intent.putExtra("age",age);
                                intent.putExtra("genre",genre);
                                intent.putExtra("utype",utype);

                                Login.this.startActivity(intent);

                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login Failed").setNegativeButton("Retry",null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(email,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });
    }
}
