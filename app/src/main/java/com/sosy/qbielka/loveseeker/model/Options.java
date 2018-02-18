package com.sosy.qbielka.loveseeker.model;

import static com.sosy.qbielka.loveseeker.OptionsMenu.BOARD_ROW_PREF_KEY;

/**
 * Singleton for board options
 * Created by Quince Bielka on 2018-02-15.
 */

//TODO: This should also be a model package!

public class Options {
    private static Options currentOptions = null;
    private int numHearts = 6;
    private int numRows = 4;
    private int numCols = 6;

    public static Options getInstance() {
        if(currentOptions == null){
            currentOptions = new Options();
        }
        return currentOptions;
    }

    public void setCurrentOptions(int numRows, int numCols, int numHearts) throws Exception{
        setNumRows(numRows);
        setNumCols(numCols);
        setNumHearts(numHearts);
    }

    public void setNumRows(int numRows)throws Exception{
        if(numRows < 1){
            throw new IllegalArgumentException("Invalid num Rows");
        }
        this.numRows = numRows;
    }

    public void setNumCols(int numCols) throws Exception{
        if(numCols < 1){
            throw new IllegalArgumentException("Invalid num Columns");
        }
        this.numCols = numCols;
    }

    public void setNumHearts(int numHearts) throws Exception{
        if(numHearts < 1){
            throw new IllegalArgumentException("Invalid num Hearts");
        }
        this.numHearts = numHearts;
    }

    public int getNumHearts(){
        return numHearts;
    }

    public int getNumRows(){
        return numRows;
    }

    public int getNumCols(){
        return numCols;
    }

    private Options() {
    }

}
