package com.sosy.qbielka.loveseeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class HeartSeeker extends AppCompatActivity {

    //TODO: lock all UI pages as Landscape mode only in AndroidManifest.xml

    private Board newGame = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_seeker);

        populateFromBoard();
    }

    //create Board as new game. pass in values of game size and mine number from options.
    // if user doesn't use options, use default value.
    public void populateFromBoard() {
        //rows, cols and max # of hearts to populate board is imported from Singleton in Board.java class.
        int max_rows = newGame.getMaxNumRows();
        int max_cols = newGame.getMaxNumCols();
        int max_hearts = newGame.getTotalNumHearts();

        TableLayout table = (TableLayout) findViewById(R.id.boardTable);

        for (int row = 0; row < max_rows; row++) {
            TableRow tableRow = new TableRow(this);
            //scale table to fill space
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                    ));
            table.addView(tableRow);

            for (int col = 0; col < max_cols; col++) {
                final int currRow = row;
                final int currCol = col;

                //populate board here
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

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
        //pass in button and row and col and if clicked, show heart or count scans in row and col.
        if (newGame.heartRevealed(row, col)) {
            //change background into heart
            //count++ on heartCount
        } else {
            int buttonScan = newGame.scan(row, col);
            //print out buttonScan on button
        }
    }

    //print out getNumHeartsReveal() and getScansUsed()
    //print board for move
    //make it look nice in xml

    //if click a button, scan() row and column.
    //if returns -1, it's new heart. if else, it's # of scans in that position.
    //update button clicked; if new heart, change to button of heart,  if else, print # of scan on button.
    //update #of hearts found or # of scans used.
    //check if game's won.

    //if #of hearts found is equal to # of hearts from options or default, game wins.

    //animation, then print win message
}
