package com.example.graeme.dnd5echaracterroller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class SeeCharacter extends AppCompatActivity {
    RollStringEnum rollString = SelectRoll.getRollString();
    ClassStringEnum classString = SelectClass.getClassString();
    int[] finalStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_character);

        //Add back button to action bar
        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        finalStats=RollStats.determineStats(rollString,classString);

        //Fill the values for the elements. This assumes it is opened in
        fillViewValues();

        ImageView imageView = (ImageView) findViewById(R.id.classicon);
        imageView.setImageResource(GetImage.SelectImage(classString.getClassString()));
        imageView.setAdjustViewBounds(true);

        //Save generated character set to memory of phone
        String ROLL_HISTORY_FILE = "roll_history";

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(ROLL_HISTORY_FILE,Context.MODE_APPEND)));
            bw.write(classString.getClassString());//Write class type
            for (int stat: finalStats){//Write rolled stats in order
                bw.write(" " +String.valueOf(stat));
            }
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("File error","Unable to write to file");
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_see_character, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void finishCharacter(View view){
        Intent finishIntent=new Intent(SeeCharacter.this, SelectPath.class);
        startActivity(finishIntent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_see_character);
            fillViewValues();
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.activity_see_character);
            fillViewValues();
        }
    }

    private void fillViewValues() {
        //Gather views
        LinearLayout llClass = (LinearLayout)findViewById(R.id.classValues);
        LinearLayout llStr = (LinearLayout)findViewById(R.id.strValues);
        LinearLayout llDex = (LinearLayout)findViewById(R.id.dexValues);
        LinearLayout llCon = (LinearLayout)findViewById(R.id.conValues);
        LinearLayout llInt = (LinearLayout)findViewById(R.id.intValues);
        LinearLayout llWis = (LinearLayout)findViewById(R.id.wisValues);
        LinearLayout llCha = (LinearLayout)findViewById(R.id.chaValues);

        //Get the layout's locations
        TextView classNameView = (TextView) llClass.findViewById(R.id.statName);
        TextView classValView = (TextView) llClass.findViewById(R.id.statVal);
        ImageView classImage = (ImageView)  llClass.findViewById(R.id.classicon);

        TextView strNameView = (TextView) llStr.findViewById(R.id.statName);
        TextView strValView = (TextView) llStr.findViewById(R.id.statVal);

        TextView dexNameView = (TextView) llDex.findViewById(R.id.statName);
        TextView dexValView = (TextView) llDex.findViewById(R.id.statVal);

        TextView conNameView = (TextView) llCon.findViewById(R.id.statName);
        TextView conValView = (TextView) llCon.findViewById(R.id.statVal);

        TextView intNameView = (TextView) llInt.findViewById(R.id.statName);
        TextView intValView = (TextView) llInt.findViewById(R.id.statVal);

        TextView wisNameView = (TextView) llWis.findViewById(R.id.statName);
        TextView wisValView = (TextView) llWis.findViewById(R.id.statVal);

        TextView chaNameView = (TextView) llCha.findViewById(R.id.statName);
        TextView chaValView = (TextView) llCha.findViewById(R.id.statVal);

        //Fill those values
        //Names of sections
        classNameView.setText(getResources().getString(R.string.classString));
        strNameView.setText(getResources().getString(R.string.strString));
        dexNameView.setText(getResources().getString(R.string.dexString));
        conNameView.setText(getResources().getString(R.string.conString));
        intNameView.setText(getResources().getString(R.string.intString));
        wisNameView.setText(getResources().getString(R.string.wisString));
        chaNameView.setText(getResources().getString(R.string.chaString));

        //Values
        classValView.setText(classString.getClassString());
        classImage.setImageResource(GetImage.SelectImage(classString.getClassString()));

        strValView.setText(String.format("%d", finalStats[0]));
        dexValView.setText(String.format("%d", finalStats[1]));
        conValView.setText(String.format("%d", finalStats[2]));
        intValView.setText(String.format("%d", finalStats[3]));
        wisValView.setText(String.format("%d", finalStats[4]));
        chaValView.setText(String.format("%d", finalStats[5]));
    }


}
