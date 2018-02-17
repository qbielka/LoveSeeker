package com.sosy.qbielka.loveseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {

    //TODO: lock all UI pages as Landscape mode only in AndroidManifest.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ImageButton helpBtn =  findViewById(R.id.help_btn);
        ImageButton playBtn =  findViewById(R.id.play_btn);
        ImageButton optionsBtn =  findViewById(R.id.options_btn);

        btnPress(helpBtn, HelpMenu.makeIntent(MainMenu.this));

        btnPress(playBtn, HeartSeeker.makeIntent(MainMenu.this));

        btnPress(optionsBtn, OptionsMenu.makeIntent(MainMenu.this));
    }


    private void btnPress(ImageButton optionsBtn, final Intent intent) {
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("main_menu", "clicked the heart seeker button from main");
                startActivity(intent);
            }
        });
    }


}
