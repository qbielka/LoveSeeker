package com.sosy.qbielka.loveseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        startActivity(new Intent(WelcomeScreen.this, OptionsMenu.class));
    }

    //TODO: Create welcoming animation with skip option available as a button.
}
