package com.example.graeme.dnd5echaracterroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RollArrayAdapter extends ArrayAdapter<RollStringEnum> {
    private final Context context;
    private final RollStringEnum[] values;

    public RollArrayAdapter(Context context, RollStringEnum[] values) {
        super(context, R.layout.roll_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.roll_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rollname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rollicon);
        textView.setText(values[position].getRollString());

        // Change icon based on name
        imageView.setImageResource(values[position].getRollIcon());
        return rowView;
    }
}