package com.example.graeme.dnd5echaracterroller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

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

        //ImageView imageView = (ImageView) findViewById(R.id.classicon);
        //imageView.setImageResource(classString.getClassIcon());
        //imageView.setAdjustViewBounds(true);

        //Save generated character set to memory of phone
        String ROLL_HISTORY_FILE = "roll_history";
        writeToCharactersFile(ROLL_HISTORY_FILE);
    }

    private void writeToCharactersFile(String ROLL_HISTORY_FILE){
        //Read from your save file - if we have 10 chars already saved, remove the least recent.
        ArrayList<String> characterArray = new ArrayList<>();//Array of characters' strings: Classname Str Dex Con Wis Int Cha
        try {//Read strings in from file
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(openFileInput(ROLL_HISTORY_FILE))));
            String line;
            do {//Read all characters in from file
                line = br.readLine();
                if (line!=null){
                    characterArray.add(line);
                }
            }while (line!=null);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int maxCharactersInFile = 10;

        //Write your newly generated class to the save file
        try {
            if (characterArray.size()>=maxCharactersInFile){
            //If our file has more than 10 characters, cut it to 9 most recent, then insert newest
                //Cut out the 9 most recent characters to carry over
                List<String> subCharacterArray = characterArray.subList(characterArray.size()-maxCharactersInFile+1, characterArray.size());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(ROLL_HISTORY_FILE, Context.MODE_PRIVATE)));
                //Write the old characters
                for (String character:subCharacterArray){
                    //For existing characters just put them back in the file
                    bw.write(character);
                    bw.newLine();
                }
                //Write the new character
                String character = classString.getClassString();
                for (int stat : finalStats) {//Write rolled stats in order
                    character+=" "+String.valueOf(stat);
                }
                bw.write(character);
                bw.newLine();//End of new character
                bw.close();
            }
            else {//If our file doesn't have 10 characters, just append to it
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(ROLL_HISTORY_FILE, Context.MODE_APPEND)));
                bw.write(classString.getClassString());//Write class type
                for (int stat : finalStats) {//Write rolled stats in order
                    bw.write(" " + String.valueOf(stat));
                }
                bw.newLine();
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        inflater.inflate(R.menu.general_menu, menu);
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
        //ImageView classImage = (ImageView)  llClass.findViewById(R.id.classicon);

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
        //classImage.setImageResource(classString.getClassIcon());

        strValView.setText(String.format("%d", finalStats[0]));
        dexValView.setText(String.format("%d", finalStats[1]));
        conValView.setText(String.format("%d", finalStats[2]));
        intValView.setText(String.format("%d", finalStats[3]));
        wisValView.setText(String.format("%d", finalStats[4]));
        chaValView.setText(String.format("%d", finalStats[5]));
    }


}
