package com.sosy.qbielka.loveseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OptionsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);
    }

    //TODO: create radio buttons for options choices

    //TODO: implement Singleton class Options.java for saving information between sessions purposes

    //TODO: press back to return to main menu

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionsMenu.class);
    }
}
