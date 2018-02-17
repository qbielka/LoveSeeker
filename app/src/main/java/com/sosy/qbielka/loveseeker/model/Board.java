package com.sosy.qbielka.loveseeker.model;

/**
 * Created by Quince Bielka on 2018-02-14.
 * Holds a state of the board
 */

//TODO: move to model package

public class Board {
    public static final int SENTENTIAL = -1;
    private Tile board[][];
    private boolean boardUISeen[][];
    private int boardNumbers[][];
    private int maxNumRows;
    private int maxNumCols;
    private int scansUsed;
    private int numHeartsRevealed;
    private int totalNumHearts;

    public Board(){
        scansUsed = 0;
        numHeartsRevealed = 0;
        Options loadCurrentOptions = Options.getInstance();
        totalNumHearts = loadCurrentOptions.getNumHearts();
        maxNumRows = loadCurrentOptions.getNumRows();
        maxNumCols = loadCurrentOptions.getNumCols();
        board = new Tile[maxNumRows][maxNumCols];
        boardUISeen = new boolean[maxNumRows][maxNumCols];
        boardNumbers = new int[maxNumRows][maxNumCols];
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

    public int getNumHeartsRowCol(int row, int col){
        return boardNumbers[row][col];
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

    public int scan(int row, int col) throws Exception{
        boundsCheck(row, col);

        if(!boardUISeen[row][col]) {
            boardUISeen[row][col] = true;
            scansUsed++;
        }


        int sum = 0;

        sum = getScan(row, col, sum);

        boardNumbers[row][col] = sum;
        return sum;
    }

    private void updateBoardUISeen(boolean[] booleans, int col) {
        if(!booleans[col]) {
            booleans[col] = true;
            scansUsed++;
        }
    }


    private void foundHeart(int row, int col) throws Exception {
        for(int x = 0; x < maxNumRows; x++){
            if(x == row){
                continue;
            }
            if(boardUISeen[x][col]){
                updateBoardNums(x, col);
            }
        }
        for(int y = 0; y < maxNumCols; y++){
            if(y == col){
                continue;
            }
            if(boardUISeen[row][y]){
                updateBoardNums(row, y);
            }
        }
    }

    private int updateBoardNums(int row, int col) throws Exception{
        boundsCheck(row, col);

        updateBoardUISeen(boardUISeen[row], col);

        int sum = 0;

        sum = getScan(row, col, sum);

        boardNumbers[row][col] = sum;
        return sum;
    }

    private int getScan(int row, int col, int sum) {
        for(int x = 0; x < maxNumRows; x++){
            if(board[x][col] == Tile.HEART && !boardUISeen[x][col]){
                sum++;
            }
        }
        for(int y = 0; y < maxNumCols; y++){

            if(board[row][y] == Tile.HEART && !boardUISeen[row][y]){
                sum++;
            }
        }
        return sum;
    }

    private void boundsCheck(int row, int col) {
        if(row < 0 || col < 0 || row > maxNumRows || col > maxNumCols){
            throw new IllegalArgumentException("Index does not exist");
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
        for(int row = 0; row < maxNumRows; row ++){
            for(int column = 0; column < maxNumCols; column ++){
                boardNumbers[row][column] = SENTENTIAL;
            }
        }
    }

    private void makeHearts(int numHearts) {
        int hearts = 0;
        while(hearts < numHearts){
            int row = (int) (Math.random() * maxNumRows);
            int column = (int) (Math.random() * maxNumCols);
            if( board[row][column] == Tile.BLANK){
                board[row][column] = Tile.HEART;
                hearts++;
            }
        }
    }

}
