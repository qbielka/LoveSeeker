package com.sosy.qbielka.loveseeker;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        final boolean[] skipped = {false};
        // welcome screen code
        final Button skip = (Button) findViewById(R.id.skip_btn);

        skipBtn(skipped, skip);

        if(!skipped[0]) {
            bootMenuAndTerminate();
        }
    }

    private void skipBtn(final boolean[] skipped, Button skip) {
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skipped[0] = true;
                bootMenuAndTerminate();
            }
        });
    }

    private void bootMenuAndTerminate() {
        startActivity(new Intent(WelcomeScreen.this, MainMenu.class));
        finish();
    }

    //TODO: Create welcoming animation with skip option available as a button.
}
