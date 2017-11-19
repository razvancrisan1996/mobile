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

    //UserLocalStore userLocalStore = new UserLocalStore(this);
    Intent intent=getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button bLogOut = (Button) findViewById(R.id.logOutButton);


    }
    public void process(View view){
        Intent intent = null, chooser=null;
        if(view.getId() == R.id.but1){
            intent = new Intent(MainActivity.this,ListViewActivity.class);
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
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (authenticate()==true){
//
//        }
//    }
//
//    private boolean authenticate(){
//        return userLocalStore.getUserLoggedIn();
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.logOutButton:
//                userLocalStore.clearUserData();
//                userLocalStore.setUserLoggedIn(false);
//
//                startActivity(new Intent(this, Login.class));
//                break;
//        }
//    }
}
