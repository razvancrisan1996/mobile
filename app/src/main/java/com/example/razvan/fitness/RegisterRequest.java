package com.example.razvan.fitness;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Razvan on 1/4/2018.
 */

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://www.caloriesintake.ga/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, int weight,int age, User.Genre genre, User.UType utype, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("weight", weight+"");
        params.put("age",age+"");
        params.put("genre",genre+"");
        params.put("utype",utype+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
