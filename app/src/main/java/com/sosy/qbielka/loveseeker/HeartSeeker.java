package com.sosy.qbielka.loveseeker;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.sosy.qbielka.loveseeker.model.Board;
import com.sosy.qbielka.loveseeker.model.Options;

public class HeartSeeker extends AppCompatActivity {
    private static final int MILLISECOND_WAIT = 60000;

    private Board newGame = new Board();
    private Handler handler;
    
    int MAX_ROWS = newGame.getMaxNumRows();
    int MAX_COLS = newGame.getMaxNumCols();
    int MAX_HEARTS = newGame.getTotalNumHearts();

    Button[][] boardOfButtons = new Button[MAX_ROWS][MAX_COLS];

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_seeker);

        populateFromBoard();
    }

    public void populateFromBoard() {

        TextView textViewHeart = (TextView) findViewById(R.id.heartCount);
        int heartIntCount = newGame.getNumHeartsRevealed();
        textViewHeart.setText("Found " + heartIntCount + " of " + MAX_HEARTS + " hearts.");

        TextView textViewScan = (TextView) findViewById(R.id.scanCount);
        int scanIntCount = newGame.getScansUsed();
        textViewScan.setText("# Scans used: " + scanIntCount);

        Options loadCurrentOptions = Options.getInstance();

        TextView textViewRecord = (TextView) findViewById(R.id.recordCount);

        int gameNumCount = loadCurrentOptions.getNumGamesCounter();
        int currHighScore = getCurrHighScore();

        textViewRecord.setText("" + gameNumCount + "th game | High Score: " + currHighScore + " scans");

        TableLayout table = (TableLayout) findViewById(R.id.boardTable);

        for (int row = 0; row < MAX_ROWS; row++) {
            TableRow tableRow = new TableRow(this);

            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                    ));

            table.addView(tableRow);

            for (int col = 0; col < MAX_COLS; col++) {
                final int currRow = row;
                final int currCol = col;

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

        if (newGame.heartRevealed(row, col)) {

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

            lockButtonSizes(MAX_ROWS, MAX_COLS);

            int heartIntCount = newGame.getNumHeartsRevealed();

            TextView textView = (TextView) findViewById(R.id.heartCount);
            textView.setText("Found " + heartIntCount + " of " + MAX_HEARTS + " hearts.");

            newGame.foundHeart(row, col);

            for (int tempRow = 0; tempRow < MAX_ROWS; tempRow++) {
                for (int tempCol = 0; tempCol < MAX_COLS; tempCol++) {

                    Button tempButton = boardOfButtons[tempRow][tempCol];

                    int buttonScan = newGame.getNumHeartsRowCol(tempRow, tempCol);

                    if (buttonScan != -1) {
                        tempButton.setText("" + buttonScan);
                    }
                }
            }
        } else {
            int buttonScan = newGame.scan(row, col);

            button.setText("" + buttonScan);

            int scanIntCount = newGame.getScansUsed();

            TextView textView = (TextView) findViewById(R.id.scanCount);
            textView.setText("# Scans used: " + scanIntCount);
        }


        if (newGame.getNumHeartsRevealed() == MAX_HEARTS) {

            int gameNumCount = incrementNumGamesCount();

            int scanIntCount = newGame.getScansUsed();
            checkHighScore(scanIntCount);

            int currHighScore = getCurrHighScore();

            TextView textViewRecord = (TextView) findViewById(R.id.recordCount);
            textViewRecord.setText("" + gameNumCount + "th game | High Score: " + currHighScore + " scans");

            finishGame();
        }
    }


    public void lockButtonSizes(int max_row, int max_col) {

        for (int row = 0; row < max_row; row++) {
            for (int col = 0; col < max_col; col++) {

                Button button = boardOfButtons[row][col];

                int width = button.getWidth();

                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();

                button.setMinHeight(height);
                button.setMaxWidth(height);
            }
        }
    }


    private int incrementNumGamesCount() {
        Options loadCurrentOptions = Options.getInstance();
        loadCurrentOptions.incrementNumGamesCounter();

        return loadCurrentOptions.getNumGamesCounter();
    }


    private int getCurrHighScore() {
        Options loadCurrentOptions = Options.getInstance();

        int curr_highScore = 0;

        int[] optionsLoveNums = {6, 10, 15, 20};
        int[] optionsRowsNums = {4, 5, 6};

        for (int i = 0; i < optionsLoveNums.length; i++) {
            for (int j = 0; j < optionsRowsNums.length; j++) {
                if (MAX_HEARTS == optionsLoveNums[i] && MAX_ROWS == optionsRowsNums[j]) {
                     curr_highScore = loadCurrentOptions.getHighPoints(i, j);
                }
            }
        }

        return curr_highScore;
    }


    private void checkHighScore(int scanIntCount) {
        Options loadCurrentOptions = Options.getInstance();

        int[] optionsLoveNums = {6, 10, 15, 20};
        int[] optionsRowsNums = {4, 5, 6};

        for (int i = 0; i < optionsLoveNums.length; i++) {
            for (int j = 0; j < optionsRowsNums.length; j++) {
                if (MAX_HEARTS == optionsLoveNums[i] && MAX_ROWS == optionsRowsNums[j]) {
                    int curr_highScore = getCurrHighScore();

                    if (curr_highScore == 0) {
                        loadCurrentOptions.setHighPoints(i, j, scanIntCount);
                    } else if(scanIntCount < curr_highScore) {
                        loadCurrentOptions.setHighPoints(i, j, scanIntCount);
                    }
                }
            }
        }
    }


    private void finishGame() {

        FragmentManager manager = getSupportFragmentManager();
        congratsMessageFragment dialog = new congratsMessageFragment();
        dialog.show(manager, "congratulatoryMessage");

        handler=new Handler();

        Runnable r=new Runnable() {
            public void run() {
                finish();
            }
        };

        handler.postDelayed(r, MILLISECOND_WAIT);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, HeartSeeker.class);
    }
}
