package com.example.graeme.dnd5echaracterroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SelectClass extends AppCompatActivity {

    private static String classString;

    public static void setClassString(String classString) {
        SelectClass.classString = classString;
    }

    public static String getClassString() {
        return classString;
    }

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        //Initialize the available class choices
        final String[] classes = {"Barbarian","Bard","Cleric","Druid",
        "Fighter","Monk","Paladin","Ranger","Rogue","Sorcerer",
        "Warlock", "Wizard"};

        // Initialize the listview
        mListView = (ListView) findViewById(R.id.listViewId);

        //Set adapter for the listview
        ClassArrayAdapter classAdapter = new ClassArrayAdapter(this, classes);

        mListView.setAdapter(classAdapter);

        //Set on click listener to get selected class item
        AdapterView.OnItemClickListener itemClickedHandler = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                //Start a new intent headed to selectRoll, fill it with the class string selected
                Intent sendClassIntent = new Intent(SelectClass.this, SelectRoll.class);

                //Each list item has an image, and text
                //First grab the list item, then grab the text from it
                LinearLayout ll = (LinearLayout)v;
                TextView tv = (TextView)(ll).findViewById(R.id.classname);
                setClassString((String)(tv.getText()));

                startActivity(sendClassIntent);
            }
        };
        mListView.setOnItemClickListener(itemClickedHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select_class, menu);
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