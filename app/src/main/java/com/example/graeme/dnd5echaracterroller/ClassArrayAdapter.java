package com.example.graeme.dnd5echaracterroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassArrayAdapter extends ArrayAdapter<ClassStringEnum> {
    private final Context context;
    private final ClassStringEnum[] values;

    public ClassArrayAdapter(Context context, ClassStringEnum[] values) {
        super(context, R.layout.class_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.class_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.classname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.classicon);
        textView.setText(values[position].getClassString());

        // Change icon based on class
        imageView.setImageResource(values[position].getClassIcon());
        return rowView;
    }
}