package com.example.razvan.fitness;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Razvan on 1/10/2018.
 */

public class ReadAllWorkoutsRequest extends JsonObjectRequest{
    private static final String READ_REQUEST_URL = "http://www.caloriesintake.ga/ReadWorkouts.php";
    private Map<String, String> params;

    public ReadAllWorkoutsRequest(Response.Listener<JSONObject> listener){
        super(Request.Method.POST,READ_REQUEST_URL, listener, null);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
