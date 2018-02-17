package com.sosy.qbielka.loveseeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsMenu extends AppCompatActivity {

    private Options loadCurrentOptions;

    private int[] optionsLoveNums = {6, 10, 15, 20};
    int defaultLoveVal = optionsLoveNums[0];

    private int numOfBoardSizeOptions = 3;

    private int[] optionsRowsNums = {4, 5, 6};
    private int[] optionsColsNums = {6, 10, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        //implement Singleton class Options.java for saving information between sessions purposes
        loadCurrentOptions = Options.getInstance();

        createOptionMenuLove();
        int savedLoveValue = getLoveNumPreferences(this);

        createOptionMenuBoardSize();
        int rowValue = getBoardRowPreferences(this);
        int colValue = getBoardColPreferences(this);

    }

    //create radio buttons for options choices

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

                    saveLoveNumPreferences(loveNum);
                }
            });

            loveGroup.addView(button);

            if (loveNum == getLoveNumPreferences(this)) {
                button.setChecked(true);
            }
        }

    }

    private void saveLoveNumPreferences(int loveNum) {
        SharedPreferences prefs = this.getSharedPreferences("appPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("loveNumPreferences", loveNum);
        editor.apply();
    }

    static public int getLoveNumPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("appPreferences", MODE_PRIVATE);
        //TODO: get default value
        return prefs.getInt("loveNumPreferences", 0);
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
            if (rowNum == getBoardRowPreferences(this) && colNum == getBoardColPreferences(this)) {
                button.setChecked(true);
            }
        }

    }

    private void saveBoardSizePreferences(int rowNum, int colNum) {
        SharedPreferences prefs = this.getSharedPreferences("boardPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("boardRowPreferences", rowNum);
        editor.putInt("boardColPreferences", colNum);
        editor.apply();
    }

    static public int getBoardRowPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("boardPreferences", MODE_PRIVATE);
        //TODO: get default value
        return prefs.getInt("boardRowPreferences", 0);
    }

    static public int getBoardColPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("boardPreferences", MODE_PRIVATE);
        //TODO: get default value
        return prefs.getInt("boardColPreferences", 0);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionsMenu.class);
    }
}
