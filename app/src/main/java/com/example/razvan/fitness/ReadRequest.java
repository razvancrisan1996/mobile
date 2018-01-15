package com.example.razvan.fitness;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Razvan on 1/9/2018.
 */

public class ReadRequest extends JsonObjectRequest{
    private static final String READ_REQUEST_URL = "http://www.caloriesintake.ga/Read.php";
    private Map<String, String> params;

    public ReadRequest(Response.Listener<JSONObject> listener){
        super(Request.Method.POST,READ_REQUEST_URL, listener, null);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
