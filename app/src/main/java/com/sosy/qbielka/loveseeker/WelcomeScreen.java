package com.sosy.qbielka.loveseeker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.Executors.*;

public class WelcomeScreen extends AppCompatActivity {

    private static final int MILLISECOND_WAIT = 5000;
    private final boolean[] skipped = {false};
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        // welcome screen code
        final Button skip = (Button) findViewById(R.id.skip_btn);

        skipBtn(skip);

    }

    @Override
    public void onWindowFocusChanged(boolean b) {
        super.onWindowFocusChanged(b);
        handler=new Handler();
        Runnable r=new Runnable() {
            public void run() {
                autoAdvance();
            }
        };
        handler.postDelayed(r, MILLISECOND_WAIT);
    }

    private void autoAdvance() {
        if(!skipped[0]) {
            bootMenuAndTerminate();
            skipped[0] = true;
        }
    }

    private void skipBtn(Button skip) {
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!skipped[0]) {
                    bootMenuAndTerminate();
                    skipped[0] = true;
                }
            }
        });
    }

    private void bootMenuAndTerminate() {
        startActivity(new Intent(WelcomeScreen.this, MainMenu.class));
        finish();
    }
}
