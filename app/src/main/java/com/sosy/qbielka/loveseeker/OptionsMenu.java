package com.sosy.qbielka.loveseeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class OptionsMenu extends AppCompatActivity {

    private Options loadCurrentOptions;

    private int[] optionsLoveNums = {6, 10, 15, 20};

    private int[] optionsRowsNums = {4, 5, 6};
    private int[] optionsColsNums = {6, 10, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        //TODO: implement Singleton class Options.java for saving information between sessions purposes
        loadCurrentOptions = Options.getInstance();

        createOptionMenuLoveNum();
        createOptionMenuBoardSize();
    }

    //TODO: create radio buttons for options choices

    private void createOptionMenuLoveNum() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupLoveNum);


    }

    private void createOptionMenuBoardSize() {

    }

    //TODO: press back to return to main menu
}
