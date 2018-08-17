package com.gdc.isfacademy.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gdc.isfacademy.R;

/**
 * Created by ashishthakur on 7/6/18.
 */

@SuppressWarnings("ALL")
public class HighLightArrayAdapter extends ArrayAdapter<CharSequence> {

    final Context context;
    final CharSequence[] objects;
    final AppCompatSpinner spinner;


    public HighLightArrayAdapter(Context context, int resource, CharSequence[] objects, AppCompatSpinner spinner) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.spinner=spinner;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        @SuppressLint("InflateParams") View spinnerItem = inflater.inflate(R.layout.spinner_cost, null);
        TextView mytext= (TextView)spinnerItem.findViewById(R.id.spinnerItem);
        mytext.setText(objects[position]);

        //int selected = Spinner.
        int selected = spinner.getSelectedItemPosition();
        if(position == selected){
            spinnerItem.setBackgroundColor(Color.parseColor("#00DCCF"));
        }

        return spinnerItem;
    }
}
