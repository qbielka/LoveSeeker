package com.sosy.qbielka.loveseeker;

import android.app.VoiceInteractor;

/**
 * Created by Quince Bielka on 2018-02-14.
 * Holds a state of the board
 */

public class Board {
    private Tile board[][];
    private boolean boardUISeen[][];
    private int maxNumRows;
    private int maxNumCols;
    private int scansUsed;
    private int numHeartsRevealed;
    private int totalNumHearts;

    Board(){
        scansUsed = 0;
        numHeartsRevealed = 0;
        totalNumHearts = Options.getNumHearts();
        maxNumRows = Options.getNumRows();
        maxNumCols = Options.getNumCols();
        board = new Tile[maxNumRows][maxNumCols];
        boardUISeen = new boolean[maxNumRows][maxNumCols];
        makeBlank();
        makeHearts(totalNumHearts);
    }

    // getters
    public int getScansUsed(){
        return scansUsed;
    }

    public int getMaxNumRows() {
        return maxNumRows;
    }

    public int getMaxNumCols() {
        return maxNumCols;
    }

    public int getNumHeartsRevealed(){
        return numHeartsRevealed;
    }

    public int getTotalNumHearts(){
        return totalNumHearts;
    }

    public Tile getTile(int row, int col){
        return board[row][col];
    }

    //checks bounds, returns true if sees a new heart
    //else return false
    public boolean heartRevealed(int row, int col) throws Exception {
        boundsCheck(row, col);

        if(!boardUISeen[row][col] && Tile.HEART == board[row][col]){
            boardUISeen[row][col] = true;
            numHeartsRevealed++;
            return true;
        }

        return false;

    }

    // checks bounds, returns number of hidden hearts in row and column
    public int scan(int row, int col) throws Exception{
        boundsCheck(row, col);

        if(!boardUISeen[row][col]) {
            boardUISeen[row][col] = true;
            scansUsed++;
        }

        int sum = 0;
        final int UP = 1;
        final int DOWN = -1;
        sum+=getNumHiddenRecursiveCols(row, col, UP);
        sum+=getNumHiddenRecursiveCols(row, col, DOWN);
        sum+=getNumHiddenRecursiveRows(row, col, UP);
        sum+=getNumHiddenRecursiveRows(row, col, DOWN);

        return sum;
    }

    private void boundsCheck(int row, int col) {
        if(row < 0 || col < 0 || row > maxNumRows || col > maxNumCols){
            throw new IllegalArgumentException("Index does not exist");
        }
    }

    private int getNumHiddenRecursiveRows(int row, int col, int difference){
        if(row < maxNumRows && row > 0){
            if(boardUISeen[row][col]){
                return getNumHiddenRecursiveRows(row + difference, col, difference);
            }
            else if(board[row][col]==Tile.HEART){
                return getNumHiddenRecursiveRows(row + difference, col, difference) + 1;
            }
            else {
                return getNumHiddenRecursiveRows(row + difference, col, difference);
            }
        }else {
            return 0;
        }
    }

    private int getNumHiddenRecursiveCols(int row, int col, int difference){
        if(col < maxNumCols && col > 0){
            if(boardUISeen[row][col]){
                return getNumHiddenRecursiveCols(row, col + difference, difference);
            }
            else if(board[row][col]==Tile.HEART){
                return getNumHiddenRecursiveCols(row, col + difference, difference) + 1;
            }
            else {
                return getNumHiddenRecursiveCols(row, col + difference, difference);
            }
        }else {
            return 0;
        }
    }

    private void makeBlank() {
        for(int row = 0; row < maxNumRows; row ++){
            for(int column = 0; column < maxNumCols; column ++){
                board[row][column] = Tile.BLANK;
            }
        }
        for(int row = 0; row < maxNumRows; row ++){
            for(int column = 0; column < maxNumCols; column ++){
                boardUISeen[row][column] = false;
            }
        }
    }

    private void makeHearts(int numHearts) {
        int hearts = 0;
        while(hearts < numHearts){
            int row = (int) (Math.random() * maxNumCols);
            int column = (int) (Math.random() * maxNumRows);
            if( board[row][column] == Tile.BLANK){
                board[row][column] = Tile.HEART;
                hearts++;
            }
        }
    }

}
