package com.example.graeme.dnd5echaracterroller;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SeePreviousCharacters extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int pageCount;
    private ArrayList<String> previousChars;
    public static final String EMPTY_FILE_STRING = "EMPTY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_previous_characters);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar()!=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        previousChars = new ArrayList<>();
        ArrayList<String> tmp = new ArrayList<>();
        String ROLL_HISTORY_FILE = "roll_history";
        try {//Read strings in from file
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(openFileInput(ROLL_HISTORY_FILE))));
            String line;
            do {//Read all characters in from file
                line = br.readLine();
                if (line!=null){
                    tmp.add(line);
                    System.out.println(line);
                }
            }while (line!=null);

            for (int i=tmp.size()-1;i>=0;i--){//Reverse the list so we get most recent characters
                previousChars.add(tmp.get(i));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("File error", "Unable to read file - FNF");
        }
        //Then update the number of pages that we need to display
        pageCount = previousChars.size();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_see_previous_characters, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (pageCount==0){
                return PlaceholderFragment.newInstance( EMPTY_FILE_STRING );
            }
            return PlaceholderFragment.newInstance( previousChars.get(position) );
        }

        @Override
        public int getCount() {
            // Return the value we determined earlier from our file's number of characters
            if (pageCount==0){//If we have an empty file, tell them to make a character
                return 1;
            }
            return pageCount;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String CHAR_STRING = "class_string";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String classString) {
            //Return data for this version of a page
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(CHAR_STRING, classString);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View pageView;

            String charString=getArguments().getString(CHAR_STRING);//Get character string values from the file
            assert charString!=null;
            if (charString.equals(EMPTY_FILE_STRING)){//If the file is empty, show that there are no characters
                Log.i("Empty string","Empty string from file");
                pageView = inflater.inflate(R.layout.fragment_no_previous_characters, container, false);
            }
            else {//If it has characters, create the character layout and fill it
                pageView = inflater.inflate(R.layout.fragment_see_previous_characters, container, false);
                fillViewValues(pageView, charString);
            }

            return pageView;
        }

        private void fillViewValues(View pageView, String charString) {
            //Gather rows of view
            LinearLayout llClass = (LinearLayout)pageView.findViewById(R.id.classValues);
            LinearLayout llStr = (LinearLayout)pageView.findViewById(R.id.strValues);
            LinearLayout llDex = (LinearLayout)pageView.findViewById(R.id.dexValues);
            LinearLayout llCon = (LinearLayout)pageView.findViewById(R.id.conValues);
            LinearLayout llInt = (LinearLayout)pageView.findViewById(R.id.intValues);
            LinearLayout llWis = (LinearLayout)pageView.findViewById(R.id.wisValues);
            LinearLayout llCha = (LinearLayout)pageView.findViewById(R.id.chaValues);

            //Get the layout's locations within a row
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
            String classString;
            int[] finalStats = new int[6];
            String[] splitCharString = charString.split(" ");
            classString=splitCharString[0];
            for (int i=0;i<6;i++){
                finalStats[i] = Integer.parseInt(splitCharString[i+1]);
            }

            classValView.setText(classString);
            classImage.setImageResource(GetImage.SelectImage(classString));
            strValView.setText(String.format("%d", finalStats[0]));
            dexValView.setText(String.format("%d", finalStats[1]));
            conValView.setText(String.format("%d", finalStats[2]));
            intValView.setText(String.format("%d", finalStats[3]));
            wisValView.setText(String.format("%d", finalStats[4]));
            chaValView.setText(String.format("%d", finalStats[5]));
        }
    }
}
