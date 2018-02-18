package com.sosy.qbielka.loveseeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sosy.qbielka.loveseeker.model.Options;

public class OptionsMenu extends AppCompatActivity {

    public static final String BOARD_ROW_PREF_KEY = "boardRowPreferences";
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

        TextView textView = (TextView) findViewById(R.id.title_options);
        textView.setText("Options");

        //implement Singleton class Options.java for saving information between sessions purposes
        loadCurrentOptions = Options.getInstance();

        createOptionMenuLove();
        int savedLoveValue = getLoveNumPreferences(this);

        createOptionMenuBoardSize();
        int rowValue = getBoardRowPreferences(this);
        int colValue = getBoardColPreferences(this);

        //additional features: reset high points and games number counter
        resetHighPoints();
        resetNumGamesCounter();

    }

    //additional features: reset number of games played
    private void resetNumGamesCounter() {
        Button button = (Button) findViewById(R.id.resetGamesCounterBtn);
        button.setText("Reset Games Counter");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCurrentOptions.resetNumGamesCounter();
            }
        });

    }

    //additional features: reset high points
    private void resetHighPoints() {
        Button button = (Button) findViewById(R.id.resetHighPointsBtn);
        button.setText("Reset High Points");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCurrentOptions.resetHighPoints();
            }
        });

    }

    //create radio buttons for options choices

    private void createOptionMenuLove() {
        RadioGroup loveGroup = (RadioGroup) findViewById(R.id.radioGroupLoveNum);

        for (int option = 0; option < optionsLoveNums.length; option++) {
            final int loveNum = optionsLoveNums[option];

            RadioButton button = new RadioButton(this);
            button.setText(("" + loveNum + " loves"));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        loadCurrentOptions.setNumHearts(loveNum);
                    }catch (Exception e){}
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

        return prefs.getInt("loveNumPreferences", 6);
    }

    private void createOptionMenuBoardSize() {
        RadioGroup boardSizeGroup = (RadioGroup) findViewById(R.id.radioGroupBoardSize);

        for (int option = 0; option < numOfBoardSizeOptions; option++) {
            final int rowNum = optionsRowsNums[option];
            final int colNum = optionsColsNums[option];

            RadioButton button = new RadioButton(this);
            button.setText(("" + rowNum + " rows, " + colNum + " cols"));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        loadCurrentOptions.setNumRows(rowNum);
                        loadCurrentOptions.setNumCols(colNum);
                    }catch(Exception e){}
                    saveBoardSizePreferences(rowNum,colNum);
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
        return prefs.getInt(BOARD_ROW_PREF_KEY, 4);
    }

    static public int getBoardColPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("boardPreferences", MODE_PRIVATE);
        return prefs.getInt("boardColPreferences", 6);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionsMenu.class);
    }
}
