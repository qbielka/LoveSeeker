package com.sosy.qbielka.loveseeker;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.sosy.qbielka.loveseeker.model.Board;

public class HeartSeeker extends AppCompatActivity {
    private static final String HSTAG = "HeartSeeker";

    private Board newGame = new Board();

    int MAX_ROWS = newGame.getMaxNumRows();
    int MAX_COLS = newGame.getMaxNumCols();
    int MAX_HEARTS = newGame.getTotalNumHearts();

    Button[][] boardOfButtons = new Button[MAX_ROWS][MAX_COLS];

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(HSTAG, "Starts");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("heartseeker", "onCreate hit");
        super.onCreate(savedInstanceState);
        Log.d("heartseeker", "super.onCreate hit");
        setContentView(R.layout.activity_heart_seeker);
        Log.d("heartseeker", "setContentView hit");

        populateFromBoard();
    }

    //create Board as new game. pass in values of game size and mine number from options.
    // if user doesn't use options, use default value.
    public void populateFromBoard() {
        Log.d("heartseeker", "populateFromBoard hit");
        //rows, cols and max # of hearts to populate board is imported from Singleton in Board.java class.

        TableLayout table = (TableLayout) findViewById(R.id.boardTable);
        for (int row = 0; row < MAX_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            //scale table to fill space
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                    ));
            table.addView(tableRow);

            for (int col = 0; col < MAX_COLS; col++) {
                final int currRow = row;
                final int currCol = col;

                //populate board here
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                boardOfButtons[row][col] = button;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //this try catch block is to catch exception in bounds checking of Board.java
                        try {
                            populateButton(currRow, currCol);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                tableRow.addView(button);
            }
        }
    }

    public void populateButton(int row, int col) throws Exception {

        Button button = boardOfButtons[row][col];
        //pass in row and col and if clicked, show heart or count scans in row and col.

        //lock sizes of buttons
        lockButtonSizes(row, col);

        if (newGame.heartRevealed(row, col)) {
            //change background into heart
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                int backgroundWidth = button.getWidth();
                int backgroundHeight = button.getHeight();
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_heart_background);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, backgroundWidth, backgroundHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            } else {
                button.setBackgroundResource(R.mipmap.ic_heart_background);
            }

            //count++ on heartCount
            TextView textView = (TextView) findViewById(R.id.heartCount);
            int heartIntCount = newGame.getNumHeartsRevealed();
            textView.setText("Found " + heartIntCount + " of " + MAX_HEARTS + "mines.");
        } else {
            int buttonScan = newGame.scan(row, col);

            //print out buttonScan on button
            button.setText("" + buttonScan);

            //count++ on scanCount
            TextView textView = (TextView) findViewById(R.id.scanCount);
            int scanIntCount = newGame.getScansUsed();
            textView.setText("# Scans used: " + scanIntCount);
        }

        //check if game's won
        if (newGame.getNumHeartsRevealed() == MAX_HEARTS) {
            //game is over
            //TODO: finish game, call dialogue, go back to Main menu
        }
    }

    public void lockButtonSizes(int max_row, int max_col) {
        for (int row = 0; row < max_row; row++) {
            for (int col = 0; col < max_col; col++) {
                Button button = boardOfButtons[row][col];

                //lock button size
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxWidth(height);
            }
        }
    }

    //TODO: congratulatory dialogue if game's won: an image and a text

    public static Intent makeIntent(Context context) {
        return new Intent(context, HeartSeeker.class);
    }
}
