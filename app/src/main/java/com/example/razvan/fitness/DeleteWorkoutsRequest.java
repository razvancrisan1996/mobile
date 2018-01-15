package com.example.razvan.fitness;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Razvan on 1/10/2018.
 */

public class DeleteWorkoutsRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://www.caloriesintake.ga/DeleteWorkout.php";
    private Map<String, String> params;

    public DeleteWorkoutsRequest(int idWorkout, Response.Listener<String> listener){
        super(Request.Method.POST,REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idWorkout", idWorkout+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
