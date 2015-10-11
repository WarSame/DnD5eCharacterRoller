package com.example.graeme.dnd5echaracterroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RollArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public RollArrayAdapter(Context context, String[] values) {
        super(context, R.layout.rolllayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rolllayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rollname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rollicon);
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        switch (s){
            case "4d6 Drop Worst Roll":
                imageView.setImageResource(R.drawable.fourd6b350);
                break;
            case "3d6 Assign As Rolled":
                imageView.setImageResource(R.drawable.threed6unassigned50);
                break;
            case "3d6":
                imageView.setImageResource(R.drawable.threed6assigned50);
                break;
        }

        return rowView;
    }
}