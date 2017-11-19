package com.example.razvan.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
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

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity  {
    private static final String TAG = "ListViewActivity";
    private ArrayList<Workout> workouts;
    WorkoutListAdapter adapter;
    String[] LevelUpdate = {"LOW", "LOW_MODERATE", "MODERATE", "MODERATE_HIGH", "HIGH"};
    SwipeMenuListView mListView;
    Button bLogOut;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadData();
        Log.d(TAG, "onCreate: Started.");
        mListView = (SwipeMenuListView) findViewById(R.id.listView);




        adapter = new WorkoutListAdapter(this, R.layout.adapter_view_layout, workouts);
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
                        intent.putExtra("id", String.valueOf(work.getId()));
                        intent.putExtra("description", work.getDescription());
                        intent.putExtra("duration", String.valueOf(work.getDuration()));
                        intent.putExtra("difficulty", String.valueOf(work.getDifficulty()));
                        intent.putExtra("position", String.valueOf(position));
                        startActivityForResult(intent, 0);
                        break;
                    case 1:
                        workouts.remove(position);
                        saveData(workouts);
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
                            Workout workout6 = new Workout(mDescription.getText().toString(), Integer.parseInt(mDuration.getText().toString()), Workout.Level.valueOf(mDifficulty.getSelectedItem().toString()));
                            workouts.add(workout6);
                            saveData(workouts);
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
                for (Workout X : workouts) {
                    descDurations.put(X.getDescription(), X.getDuration());
                }
                intent.putExtra("descDuration", descDurations);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int pos = -1;
        String duration = "";
        String difficulty = "";
        String position = "";

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                duration = data.getStringExtra("duration");
                difficulty = data.getStringExtra("difficulty");
                position = data.getStringExtra("position");
            }
        }
        if (position != "") {
            pos = Integer.parseInt(position);
            workouts.get(pos).setDuration(Integer.parseInt(duration));
            workouts.get(pos).setDifficulty(Workout.Level.valueOf(difficulty));
            saveData(workouts);
            mListView.setAdapter(adapter);
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



