package com.example.graeme.dnd5echaracterroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public ClassArrayAdapter(Context context, String[] values) {
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
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        switch (s){
            case "Barbarian":
                imageView.setImageResource(R.drawable.barbarianicon50);
                break;
            case "Bard":
                imageView.setImageResource(R.drawable.bardicon50);
                break;
            case "Cleric":
                imageView.setImageResource(R.drawable.clericicon50);
                break;
            case "Druid":
                imageView.setImageResource(R.drawable.druidicon50);
                break;
            case "Fighter":
                imageView.setImageResource(R.drawable.fightericon50);
                break;
            case "Monk":
                imageView.setImageResource(R.drawable.monkicon50);
                break;
            case "Paladin":
                imageView.setImageResource(R.drawable.paladinicon50);
                break;
            case "Ranger":
                imageView.setImageResource(R.drawable.rangericon50);
                break;
            case "Rogue":
                imageView.setImageResource(R.drawable.rogueicon50);
                break;
            case "Sorcerer":
                imageView.setImageResource(R.drawable.sorcerericon50);
                break;
            case "Warlock":
                imageView.setImageResource(R.drawable.warlockicon50);
                break;
            case "Wizard":
                imageView.setImageResource(R.drawable.wizardicon50);
                break;

        }

        return rowView;
    }
}