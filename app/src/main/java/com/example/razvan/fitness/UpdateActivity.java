package com.example.razvan.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class UpdateActivity extends ListViewActivity {

    EditText tvDuration;
    MaterialBetterSpinner tvDifficulty;
    String duration, difficulty, position,id;
    Button btnUpdate;
    String [] LevelUpdate = {"LOW", "LOW_MODERATE", "MODERATE", "MODERATE_HIGH", "HIGH"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, LevelUpdate);
        tvDifficulty = (MaterialBetterSpinner) findViewById(R.id.tvDifficulty);
        tvDifficulty.setAdapter(arrayAdapter);

        tvDuration = (EditText) findViewById(R.id.tvDuration);

        final Intent intent = getIntent();
        if (intent != null){

            duration = intent.getStringExtra("duration");
            difficulty = intent.getStringExtra("difficulty");
            position = intent.getStringExtra("position");
            id = intent.getStringExtra("id");

        }
        tvDifficulty.setText(difficulty);
        tvDuration.setText(duration);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("duration",tvDuration.getText().toString());
                intent.putExtra("difficulty", tvDifficulty.getText().toString());
                intent.putExtra("position", position);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
