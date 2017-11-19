package com.example.razvan.fitness;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class MyChart extends AppCompatActivity {

    private static String TAG = "MyChart";
    PieChart pieChart;
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<Integer> durations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        Intent intent = getIntent();
        HashMap<String, Integer> hashMap = (HashMap<String, Integer>)intent.getSerializableExtra("descDuration");

        for (String d : hashMap.keySet()) {
            descriptions.add(d);
            durations.add(hashMap.get(d));
        }


        pieChart = (PieChart) findViewById(R.id.pieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Workouts duration");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        addDataSet();
    }

    private void addDataSet(){
        ArrayList<PieEntry> cDurations = new ArrayList<>();

        for (int i=0; i< durations.size();i++){
            cDurations.add(new PieEntry(durations.get(i),i));
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(cDurations,"Workout durations");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

}
