package com.example.graeme.dnd5echaracterroller;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SeeCharacter extends AppCompatActivity {


    String rollString = SelectRoll.getRollString();
    String classString = SelectClass.getClassString();
    int[] finalStats = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_character);

        //Add back button to action bar
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e){
            System.out.println("Nullpointer action bar");
        }

        ArrayList<Integer> rollList = new ArrayList<>();
        ArrayList<Integer> statList = new ArrayList<>();

        if (rollString != null && rollString.equals("4d6 Drop Worst Roll")){
            //For 4d6 drop one
            //Roll 4 dice for each stat
            for (int j=0;j<6;j++) {
                for (int i = 0; i < 4; i++) {
                    rollList.add(rollD6());
                }
                //Remove the lowest roll and sum the rest, then add it to the list of stats
                rollList.remove(Collections.min(rollList));
                statList.add(sum(rollList));
                rollList=new ArrayList<>();
            }
            //Assort the stats based upon class preference
            finalStats = assignStats(statList, classString);
        }

        else if (rollString!=null&&rollString.equals("3d6")){
            //For 3d6 assign
            for (int j=0;j<6;j++) {
                for (int i = 0; i < 3; i++) {
                    rollList.add(rollD6());
                }
                //Add the sum of the rolls to the stat list
                statList.add(sum(rollList));
                rollList=new ArrayList<>();
            }
            //Assort the stats upon class preference
            finalStats = assignStats(statList, classString);
        }
        else if (rollString!=null&&rollString.equals("3d6 Assign As Rolled")) {
            //For 3d6, assign as rolled in order
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 3; i++) {
                    rollList.add(rollD6());
                }
                //Add the sum of the rolls to the stat list
                statList.add(sum(rollList));
                rollList = new ArrayList<>();
            }
            //Do not assort the stats - assign them in the order they are rolled
            for (int i = 0; i<statList.size();i++){
                finalStats[i] = statList.get(i);
            }
        }
        else {
            //In case of a null rollString, show the user the error by returning all 0s as stats
            for (int i = 0; i<statList.size();i++){
                finalStats[i] = 0;
            }
        }

        //Fill the values for the elements. This assumes it is opened in
        fillVerticalValues();

        ImageView imageView = (ImageView) findViewById(R.id.classicon);

        switch (classString){
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
        imageView.setAdjustViewBounds(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private Integer rollD6() {
        //Rolls a D6
        Random roller = new Random();
        return Math.abs(roller.nextInt() % 6)+1;
    }

    private int sum(ArrayList<Integer> rollList) {
        //Sums the elements of a list
        int i;
        int sum = 0;
        for(i = 0; i < rollList.size(); i++) {
            sum += rollList.get(i);
        }
        return sum;
    }

    private int[] assignStats(ArrayList<Integer> statList, String classString) {
        //Given the stats that have been rolled, and the class name
        //This function orders the stats in the optimal order for that class
        Integer[] statPriority = new Integer[]{};
        switch (classString) {
            case "Barbarian":
                statPriority= new Integer[]{0, 2, 1, 5, 3, 4};
                break;
            case "Bard":
                statPriority= new Integer[]{4, 2, 1, 5, 3, 0};
                break;
            case "Cleric":
                statPriority= new Integer[]{3, 2, 1, 5, 0, 4};
                break;
            case "Druid":
                statPriority= new Integer[]{5, 1, 2, 4, 0, 3};
                break;
            case "Fighter":
                statPriority= new Integer[]{0, 3, 1, 4, 2, 5};
                break;
            case "Monk":
                statPriority= new Integer[]{3, 0, 2, 5, 1, 4};
                break;
            case "Paladin":
                statPriority= new Integer[]{0, 4, 2, 5, 3, 1};
                break;
            case "Ranger":
                statPriority= new Integer[]{4, 0, 1, 3, 2, 5};
                break;
            case "Rogue":
                statPriority= new Integer[]{4, 0, 2, 5, 3, 1};
                break;
            case "Sorcerer":
                statPriority= new Integer[]{5, 1, 2, 3, 4, 0};
                break;
            case "Warlock":
                statPriority = new Integer[]{5, 1, 2, 4, 3, 0};
                break;
            case "Wizard":
                statPriority = new Integer[]{5, 1, 2, 0, 3, 4};
                break;
        }

        int[] sortedStats = new int[6];

        int index;
        for (int i =0;i<6;i++){
            //Find index of highest priority stat
            index=Arrays.asList(statPriority).indexOf(i);
            //Fill the sorted stats array at that index with the current highest remaining stat
            sortedStats[index] = Collections.max(statList);
            statList.remove(Collections.max(statList));
        }

        return sortedStats;

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


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_see_character);
            fillHorizontalValues();
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.activity_see_character);
            fillVerticalValues();
        }
    }

    private void fillHorizontalValues() {
        //Gather views for horizontal
        LinearLayout llClass = (LinearLayout)findViewById(R.id.classValues);
        LinearLayout llStr = (LinearLayout)findViewById(R.id.strValues);
        LinearLayout llDex = (LinearLayout)findViewById(R.id.dexValues);
        LinearLayout llCon = (LinearLayout)findViewById(R.id.conValues);
        LinearLayout llInt = (LinearLayout)findViewById(R.id.intValues);
        LinearLayout llWis = (LinearLayout)findViewById(R.id.wisValues);
        LinearLayout llCha = (LinearLayout)findViewById(R.id.chaValues);

        //Get the horizontal layout's locations
        TextView classNameView = (TextView) llClass.findViewById(R.id.statName);
        TextView classValView = (TextView) llClass.findViewById(R.id.statVal);

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
        classValView.setText(classString);
        strValView.setText(String.format("%d", finalStats[0]));
        dexValView.setText(String.format("%d", finalStats[1]));
        conValView.setText(String.format("%d", finalStats[2]));
        intValView.setText(String.format("%d", finalStats[3]));
        wisValView.setText(String.format("%d", finalStats[4]));
        chaValView.setText(String.format("%d", finalStats[5]));
    }

    private void fillVerticalValues() {
        //Gather class view
        TextView classView = (TextView)findViewById(R.id.classValueView);

        //Gather stat value textView locations
        TextView strengthView = (TextView)findViewById(R.id.strengthValView);
        TextView dexterityView = (TextView)findViewById(R.id.dexterityValView);
        TextView constitutionView = (TextView)findViewById(R.id.constitutionValView);
        TextView intelligenceView = (TextView)findViewById(R.id.intelligenceValView);
        TextView wisdomView = (TextView)findViewById(R.id.wisdomValView);
        TextView charismaView = (TextView)findViewById(R.id.charismaValView);

        //Fills those views with the appropriate data
        classView.setText(classString);

        strengthView.setText(String.format("%d", finalStats[0]));
        dexterityView.setText(String.format("%d", finalStats[1]));
        constitutionView.setText(String.format("%d", finalStats[2]));
        intelligenceView.setText(String.format("%d", finalStats[3]));
        wisdomView.setText(String.format("%d", finalStats[4]));
        charismaView.setText(String.format("%d", finalStats[5]));
    }

}
