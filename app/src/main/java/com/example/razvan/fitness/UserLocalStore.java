package com.example.razvan.fitness;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Razvan on 11/19/2017.
 */

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("email", user.getEmailAddress());
        spEditor.putString("password", user.getPassword());
        spEditor.putString("name", user.getName());
        spEditor.putInt("weight", user.getWeight());
        spEditor.putInt("age", user.getAge());
        spEditor.putString("genre", String.valueOf(user.getGenre()));
        spEditor.commit();
    }
    public User getLoggedInUser(){
        String email = userLocalDatabase.getString("email","");
        String password = userLocalDatabase.getString("password","");
        String name = userLocalDatabase.getString("name","");
        int weight = userLocalDatabase.getInt("weight",-1);
        int age = userLocalDatabase.getInt("age", -1);
        User.Genre genre = User.Genre.valueOf(userLocalDatabase.getString("genre",""));

        User storedUser = new User(email,password,name,weight,age,genre);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn",false)){
            return true;
        }else{
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
