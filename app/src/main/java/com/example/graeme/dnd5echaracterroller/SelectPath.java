package com.example.graeme.dnd5echaracterroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectPath extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_path);
    }

    public void rollCharacter(View v){
        Intent rollCharacterIntent = new Intent(SelectPath.this, SelectRoll.class);
        startActivity(rollCharacterIntent);
    }
    public void seePreviousCharacters(View v){
        Intent seePreviousCharactersIntent = new Intent(SelectPath.this, SeePreviousCharacters.class);
        startActivity(seePreviousCharactersIntent);
    }
}
