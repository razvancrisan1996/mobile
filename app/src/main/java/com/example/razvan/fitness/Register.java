package com.example.razvan.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    String [] GenreType = {"MALE", "FEMALE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                GenreType);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPassword);
        final EditText etWeight = (EditText) findViewById(R.id.etWeight);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final MaterialBetterSpinner tvGenre = (MaterialBetterSpinner) findViewById(R.id.tvGenre);
        tvGenre.setAdapter(arrayAdapter);

        final Button bRegister = (Button) findViewById(R.id.btRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();
                final int weight = Integer.parseInt(etWeight.getText().toString());
                final int age = Integer.parseInt(etAge.getText().toString());
                final User.Genre genre = User.Genre.valueOf(tvGenre.getText().toString());
                final User.UType utype = User.UType.USER;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(Register.this, Login.class);
                                Register.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry",null).create().show();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(email,password,weight,age,genre,utype,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });

    }
}
