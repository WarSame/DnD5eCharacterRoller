package com.example.graeme.dnd5echaracterroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SelectRoll extends AppCompatActivity {

    private static String rollString;

    public static void setRollString(String rollString) {
        SelectRoll.rollString = rollString;
    }

    private ListView mListView;

    public static String getRollString() {
        return rollString;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_roll);
        //Add back button to action bar
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e){
            System.out.println("Nullpointer action bar");
        }

        // Initialize the listview
        mListView = (ListView) findViewById(R.id.listViewId);

        //Initialize the roll options available, then fill them into the list view
        final String[] rolls = {"4d6 Drop Worst Roll", "3d6", "3d6 Assign As Rolled"};

        RollArrayAdapter rollAdapter = new RollArrayAdapter(this, rolls);

        mListView.setAdapter(rollAdapter);


        //Set an onClick to get the item selected by user
        AdapterView.OnItemClickListener itemClickedHandler = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                //On click, start a new intent going to seeCharacter, include the previous intent data
                Intent sendRollIntent = new Intent(SelectRoll.this, SeeCharacter.class);

                //Each list item has an image, and text
                //First grab the list item, then grab the text from it
                LinearLayout ll = (LinearLayout)v;
                TextView tv = (TextView)(ll).findViewById(R.id.rollname);
                setRollString((String) (tv.getText()));

                startActivity(sendRollIntent);
            }
        };

        mListView.setOnItemClickListener(itemClickedHandler);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select_roll, menu);
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
}