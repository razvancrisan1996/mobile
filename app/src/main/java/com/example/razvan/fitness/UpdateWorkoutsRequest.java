package com.example.razvan.fitness;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Razvan on 1/10/2018.
 */

public class UpdateWorkoutsRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://www.caloriesintake.ga/UpdateWorkout.php";
    private Map<String, String> params;

    public UpdateWorkoutsRequest(int idUser, String description , int duration, Workout.Level difficulty,int idWorkout, Response.Listener<String> listener){
        super(Request.Method.POST,REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("idUser", idUser+"" );
        params.put("description", description);
        params.put("duration",duration+"");
        params.put("difficulty",difficulty+"");
        params.put("idWorkout",idWorkout+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
