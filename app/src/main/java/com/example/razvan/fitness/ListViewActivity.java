package com.example.razvan.fitness;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity  {
    private static final String TAG = "ListViewActivity";
    private ArrayList<Workout> workouts;
    private ArrayList<Workout> userWorkouts;
    WorkoutListAdapter adapter;
    String[] LevelUpdate = {"LOW", "LOW_MODERATE", "MODERATE", "MODERATE_HIGH", "HIGH"};
    SwipeMenuListView mListView;
    Intent intent;
    String user_id,uType, reload;
    int totalDuration;
    NotificationCompat.Builder notification;
    private static final int uniqueID = 6969;
    RequestQueue requestQueue;
    ArrayList<Workout> incercare = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        reload = new String("TRUE");

        intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        uType = intent.getStringExtra("utype");
        if (intent.getStringExtra("reload")!= null)
            reload = intent.getStringExtra("reload");

        loadData();
        Log.d(TAG, "onCreate: Started.");
        mListView = (SwipeMenuListView) findViewById(R.id.listView);





        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONArray workoutsJ = response.getJSONArray("workouts");
                    for (int i = 0; i < workoutsJ.length(); i++) {
                        JSONObject workout = workoutsJ.getJSONObject(i);

                        String idWorkout = workout.getString("idWorkout");
                        String idUser = workout.getString("idUser");
                        String description = workout.getString("description");
                        String duration = workout.getString("duration");
                        String difficulty = workout.getString("difficulty");

                        Workout w = new Workout(Integer.parseInt(idWorkout), Integer.parseInt(idUser), description, Integer.parseInt(duration), Workout.Level.valueOf(difficulty));
                        incercare.add(w);}
                    userWorkouts=new ArrayList<>();
                    for (Workout w:incercare){
                        if (w.getId()==Integer.parseInt(user_id)){
                            userWorkouts.add(w);
                            totalDuration+=w.getDuration();
                        }
                    }
                    if (totalDuration > 100 && reload.equals("TRUE")){
                        notification = new NotificationCompat.Builder(ListViewActivity.this);
                        notification.setAutoCancel(true);
                        notification.setSmallIcon(R.mipmap.ic_gym);
                        notification.setTicker("Congrats !");
                        notification.setWhen(System.currentTimeMillis());
                        notification.setContentTitle("You rock man !");
                        notification.setContentText("You have just reached 100 mins of working out with us !");
                        reload = new String("FALSE");

                        Intent intent = new Intent(ListViewActivity.this, ListViewActivity.class);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("utype",uType);
                        intent.putExtra("reload",reload);

                        PendingIntent pendingIntent = PendingIntent.getActivity(ListViewActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        notification.setContentIntent(pendingIntent);

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(uniqueID,notification.build());
                    }


                        adapter = new WorkoutListAdapter(ListViewActivity.this, R.layout.adapter_view_layout, userWorkouts);
                        mListView.setAdapter(adapter);

                        SwipeMenuCreator creator = new SwipeMenuCreator() {
                            @Override
                            public void create(SwipeMenu menu) {
                                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0XCE)));
                                openItem.setWidth(170);
                                openItem.setIcon(R.drawable.ic_update);
                                menu.addMenuItem(openItem);
                                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xf7, 0x09, 0x09)));
                                deleteItem.setIcon(R.drawable.ic_delete);
                                deleteItem.setWidth(170);
                                menu.addMenuItem(deleteItem);

                            }
                        };
                        mListView.setMenuCreator(creator);
                        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                                switch (index) {
                                    case 0:
                                        Workout work = adapter.getItem(position);
                                        Intent intent = new Intent(ListViewActivity.this, UpdateActivity.class);
                                        intent.putExtra("idWorkout",String.valueOf(work.getIdWorkout()));
                                        intent.putExtra("idUser", String.valueOf(work.getId()));
                                        intent.putExtra("description", work.getDescription());
                                        intent.putExtra("duration", String.valueOf(work.getDuration()));
                                        intent.putExtra("difficulty", String.valueOf(work.getDifficulty()));
                                        intent.putExtra("position", String.valueOf(position));
                                        System.out.println("idUser:"+work.getId()+" "+"idWorkout:"+work.getIdWorkout()+" "+"Desc: "+work.getDescription()+" "+"Diff"+work.getDifficulty()+"Duration: "+work.getDuration());
                                        startActivityForResult(intent, 0);


                                        break;
                                    case 1:
                                        Workout w = userWorkouts.get(position);
                                        userWorkouts.remove(w);
                                        workouts.remove(w);
                                        saveData(workouts);
                                        Response.Listener responseListener = new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                            }

                                        };

                                        DeleteWorkoutsRequest deleteWorkoutsRequest = new DeleteWorkoutsRequest(Integer.valueOf(w.getIdWorkout()),responseListener);
                                        RequestQueue queue = Volley.newRequestQueue(ListViewActivity.this);
                                        queue.add(deleteWorkoutsRequest);
                                        mListView.setAdapter(adapter);
                                        break;
                                }
                                return false;
                            }
                        });

                        Button btnAdd = (Button) findViewById(R.id.addButton);
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListViewActivity.this);
                                final View mView = getLayoutInflater().inflate(R.layout.dialog_add, null);
                                final Spinner mDifficulty = (Spinner) mView.findViewById(R.id.addDifficulty);
                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ListViewActivity.this,
                                        R.layout.support_simple_spinner_dropdown_item, LevelUpdate);
                                adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                                mDifficulty.setAdapter(adapter1);
                                final EditText mDescription = (EditText) mView.findViewById(R.id.addDescription);
                                final EditText mDuration = (EditText) mView.findViewById(R.id.addDuration);
                                Button mAdd = (Button) mView.findViewById(R.id.addButtonDialog);
                                mBuilder.setView(mView);
                                final AlertDialog dialog = mBuilder.create();
                                dialog.show();
                                mAdd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (!mDuration.getText().toString().isEmpty()) {
                                            Workout workout6 = new Workout(Integer.parseInt(user_id),mDescription.getText().toString(),
                                                    Integer.parseInt(mDuration.getText().toString()),
                                                    Workout.Level.valueOf(mDifficulty.getSelectedItem().toString()));
                                            workouts.add(workout6);
                                            userWorkouts.add(workout6);
                                            saveData(workouts);
                                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {

                                                        JSONObject jsonResponse = new JSONObject(response);
                                                        boolean success = jsonResponse.getBoolean("success");

                                                        if(success){

                                                        } else {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
                                                            builder.setMessage("Register Failed").setNegativeButton("Retry",null).create().show();
                                                        }



                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            };

                                            AddWorkoutsRequest addWorkoutsRequest = new AddWorkoutsRequest(Integer.valueOf(user_id),mDescription.getText().toString(),
                                                    Integer.parseInt(mDuration.getText().toString()), Workout.Level.valueOf(mDifficulty.getSelectedItem().toString()),responseListener);
                                            RequestQueue queue = Volley.newRequestQueue(ListViewActivity.this);
                                            queue.add(addWorkoutsRequest);

                                            mListView.setAdapter(adapter);
                                            dialog.dismiss();
                                        }

                                    }
                                });


                            }
                        });
                        Button btnChart = (Button) findViewById(R.id.ChartButton);
                        btnChart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ListViewActivity.this, MyChart.class);
                                HashMap<String, Integer> descDurations = new HashMap<String, Integer>();
                                for (Workout X : userWorkouts) {
                                    descDurations.put(X.getDescription(), X.getDuration());
                                }
                                intent.putExtra("descDuration", descDurations);
                                startActivity(intent);
                            }
                        });


                }catch (JSONException e) {
                    e.printStackTrace();
                }

                }
        };


        ReadAllWorkoutsRequest readAllWorkoutsRequest = new ReadAllWorkoutsRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(ListViewActivity.this);
        queue.add(readAllWorkoutsRequest);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int pos = -1;
        String duration = "";
        String difficulty = "";
        String position = "";
        String idUser = "";
        String desctiption="";
        String idWorkout="";

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                idWorkout = data.getStringExtra("idWorkout");
                idUser = data.getStringExtra("idUser");
                desctiption = data.getStringExtra("description");
                duration = data.getStringExtra("duration");
                difficulty = data.getStringExtra("difficulty");
                position = data.getStringExtra("position");
            }
        }
        if (position != "") {
            pos = Integer.parseInt(position);
            workouts.get(pos).setDuration(Integer.parseInt(duration));
            workouts.get(pos).setDifficulty(Workout.Level.valueOf(difficulty));
            userWorkouts.get(pos).setDuration(Integer.parseInt(duration));
            userWorkouts.get(pos).setDifficulty(Workout.Level.valueOf(difficulty));
            saveData(workouts);
            mListView.setAdapter(adapter);
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if(success){

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
                            builder.setMessage("Register Failed").setNegativeButton("Retry",null).create().show();
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            System.out.println("idUser:"+idUser+" "+"idWorkout:"+idWorkout+" "+"Desc: "+desctiption+" "+"Diff"+difficulty+"Duration: "+duration);
            UpdateWorkoutsRequest updateWorkoutsRequest = new UpdateWorkoutsRequest(Integer.valueOf(idUser),desctiption,
                    Integer.valueOf(duration), Workout.Level.valueOf(difficulty),Integer.valueOf(idWorkout),responseListener);

            RequestQueue queue = Volley.newRequestQueue(ListViewActivity.this);
            queue.add(updateWorkoutsRequest);
        }


    }

    public void saveData(ArrayList<Workout> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferances", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("Workout list", json);
        editor.apply();


    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferances", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Workout list", null);
        Type type = new TypeToken<ArrayList<Workout>>(){}.getType();
        workouts = gson.fromJson(json,type);
        if (workouts == null){
            workouts = new ArrayList<>();
        }
    }


}



