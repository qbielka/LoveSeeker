package com.sosy.qbielka.loveseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsMenu extends AppCompatActivity {

    private Options loadCurrentOptions;

    private int[] optionsLoveNums = {6, 10, 15, 20};

    private int numOfBoardSizeOptions = 3;

    private int[] optionsRowsNums = {4, 5, 6};
    private int[] optionsColsNums = {6, 10, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        //TODO: implement Singleton class Options.java for saving information between sessions purposes
        loadCurrentOptions = Options.getInstance();

        createOptionMenuLove();
        createOptionMenuBoardSize();
    }

    //TODO: create radio buttons for options choices

    private void createOptionMenuLove() {
        RadioGroup loveGroup = (RadioGroup) findViewById(R.id.radioGroupLoveNum);

        for (int option = 0; option < optionsLoveNums.length; option++) {
            final int loveNum = optionsLoveNums[option];

            RadioButton button = new RadioButton(this);
            button.setText("" + loveNum + " loves");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadCurrentOptions.setNumHearts(loveNum);
                }
            });

            loveGroup.addView(button);
        }

    }

    private void createOptionMenuBoardSize() {
        RadioGroup boardSizeGroup = (RadioGroup) findViewById(R.id.radioGroupBoardSize);

        for (int option = 0; option < numOfBoardSizeOptions; option++) {
            final int rowNum = optionsRowsNums[option];
            final int colNum = optionsColsNums[option];

            RadioButton button = new RadioButton(this);
            button.setText("" + rowNum + " rows, " + colNum + " cols");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadCurrentOptions.setNumRows(rowNum);
                    loadCurrentOptions.setNumCols(colNum);
                }
            });

            boardSizeGroup.addView(button);
        }

    }

    //TODO: press back to return to main menu

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionsMenu.class);
    }
}
