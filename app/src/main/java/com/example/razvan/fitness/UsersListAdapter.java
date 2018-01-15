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

/**
 * Created by Razvan on 1/9/2018.
 */

public class UsersListAdapter extends ArrayAdapter<User>{


    private Context mContext;
    int mResource;

    public UsersListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
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
        String emailAddress = getItem(position).getEmailAddress();
        int age = getItem(position).getAge();
        int weight = getItem(position).getWeight();

        User user = new User(emailAddress,age,weight);



        TextView tvEmailAddress = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvAge = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvWeight = (TextView) convertView.findViewById(R.id.textView3);

        tvEmailAddress.setText(emailAddress);
        tvAge.setText(String.valueOf(age));
        tvWeight.setText(String.valueOf(weight));

        return convertView;

    }
}
