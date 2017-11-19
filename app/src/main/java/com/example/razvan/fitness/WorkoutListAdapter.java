package com.example.razvan.fitness;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Razvan on 11/9/2017.
 */

public class WorkoutListAdapter extends ArrayAdapter<Workout> {

    private static final String TAG = "WorkoutListAdapter";

    private Context mContext;
    int mResource;

    public WorkoutListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Workout> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = null;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

        }
        String description = getItem(position).getDescription();
        int duration = getItem(position).getDuration();
        Workout.Level difficulty = getItem(position).getDifficulty();

        Workout workout = new Workout(description,duration,difficulty);



        TextView tvDescription = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvDuration = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvDifficulty = (TextView) convertView.findViewById(R.id.textView3);

        tvDescription.setText(description);
        tvDifficulty.setText(difficulty.toString());
        tvDuration.setText(String.valueOf(duration));

        return convertView;

    }
}
