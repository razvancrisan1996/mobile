package com.example.razvan.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;
    EditText etName,etAge,etWeight,etEmailAddress,etPassword;
    String [] genre = {"MALE", "FEMALE"};
    MaterialBetterSpinner tvGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, genre);
        tvGenre = (MaterialBetterSpinner) findViewById(R.id.tvGenre);
        tvGenre.setAdapter(arrayAdapter);

        etEmailAddress = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etName = (EditText) findViewById(R.id.etName);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etAge = (EditText) findViewById(R.id.etAge);

        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bRegister:

                String email = etEmailAddress.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                int age = Integer.valueOf(etAge.getText().toString());
                int weight = Integer.valueOf(etWeight.getText().toString());
                User.Genre genre = User.Genre.valueOf(tvGenre.getText().toString());

                User registeredData = new User(email,password,name,weight,age,genre);


                break;
        }
    }
}
