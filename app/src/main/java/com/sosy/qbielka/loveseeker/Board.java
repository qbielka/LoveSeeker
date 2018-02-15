package com.sosy.qbielka.loveseeker;

/**
 * Created by Quince Bielka on 2018-02-14.
 * Holds a state of the board
 */

public class Board {
    private final int UP = 1;
    private final int DOWN = -1;
    private Tile board[][];
    private boolean boardUISeen[][];
    private int rows;
    private int cols;
    private int scansUsed;

    Board(int numHearts, int numRows, int numCols){
        scansUsed = 0;
        rows = numRows;
        cols = numCols;
        board = new Tile[rows][cols];
        boardUISeen = new boolean[rows][cols];
        makeBlank();
        makeHearts(numHearts);
    }

    public int getScansUsed(){
        return scansUsed;
    }

    public Tile revealTile(int row, int col){
        if(!boardUISeen[row][col]) {
            boardUISeen[row][col] = true;
            scansUsed++;
            return board[row][col];
        }
        return board[row][col];
    }

    public int getNumHidden(int row, int col) throws Exception{
        if(row < 0 || col < 0 || row > rows || col > cols){
            throw new IllegalArgumentException("Index does not exist");
        }
        if(!boardUISeen[row][col]) {
            boardUISeen[row][col] = true;
            scansUsed++;
        }

        int sum = 0;
        sum+=getNumHiddenRecursiveCols(row, col, UP);
        sum+=getNumHiddenRecursiveCols(row, col, DOWN);
        sum+=getNumHiddenRecursiveRows(row, col, UP);
        sum+=getNumHiddenRecursiveRows(row, col, DOWN);

        return sum;
    }

    private int getNumHiddenRecursiveRows(int row, int col, int difference){
        if(row < rows && row > 0){
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
        if(col < cols && col > 0){
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



    // part of creation
    private void makeBlank() {
        for(int row = 0; row < rows; row ++){
            for(int column = 0; column < cols; column ++){
                board[row][column] = Tile.BLANK;
            }
        }
        for(int row = 0; row < rows; row ++){
            for(int column = 0; column < cols; column ++){
                boardUISeen[row][column] = false;
            }
        }
    }

    private void makeHearts(int numHearts) {
        int hearts = 0;
        while(hearts < numHearts){
            int row = (int) (Math.random() * cols);
            int column = (int) (Math.random() * rows);
            if( board[row][column] == Tile.BLANK){
                board[row][column] = Tile.HEART;
                hearts++;
            }
        }
    }

}
