package com.sosy.qbielka.loveseeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenu extends AppCompatActivity {

    //TODO: lock all UI pages as Landscape mode only in AndroidManifest.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    //TODO: Call WelcomeScreen

    //TODO: Create buttons to call HeartSeeker, Options and HelpMenu
}
