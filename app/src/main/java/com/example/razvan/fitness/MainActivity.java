package com.example.razvan.fitness;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    Intent intent;
    String user_id,uType,email,age,weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        uType = intent.getStringExtra("utype");
        email = intent.getStringExtra("email");
        weight = intent.getStringExtra("weight");
        age = intent.getStringExtra("age");

        Button bListUser = (Button) findViewById(R.id.bUserList);

        if (uType.equals("USER")){
            bListUser.setVisibility(View.INVISIBLE);
        }
        else{
            bListUser.setVisibility(View.VISIBLE);
        }
        Button bLogOut = (Button) findViewById(R.id.logOutButton);
        bLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogout = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(intentLogout);
                finish();
            }
        });



    }
    public void process(View view){
        Intent intent = null, chooser=null;
        if(view.getId() == R.id.but1){
            intent = new Intent(MainActivity.this,ListViewActivity.class);
            intent.putExtra("user_id",user_id);
            intent.putExtra("utype", uType);
            startActivity(intent);
        }
        if(view.getId() == R.id.sendEmail)
        {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to ={"razvancrisan1996@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Welcome to beFIT application !");
            intent.putExtra(Intent.EXTRA_TEXT,"We are very happy that you are a part of our big family !");
            intent.setType("message/rfc822");
            chooser = intent.createChooser(intent,"Send Email");
            startActivityForResult(intent,0);
        }
        if(view.getId() == R.id.bUserList)
        {
            intent = new Intent(MainActivity.this, UsersList.class);
            intent.putExtra("email",email);
            intent.putExtra("age",age);
            intent.putExtra("weight",weight);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
        }
    }

}
