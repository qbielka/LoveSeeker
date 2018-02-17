package com.sosy.qbielka.loveseeker;

/**
 * Singleton for board options
 * Created by Quince Bielka on 2018-02-15.
 */

//TODO: This should also be a model package!

class Options {
    private static Options currentOptions = null;
    private int numHearts = 6;
    private int numRows = 6;
    private int numCols = 4;

    public static Options getInstance() {
        if(currentOptions == null){
            currentOptions = new Options();
        }
        return currentOptions;
    }

    public void setCurrentOptions(int numRows, int numCols, int numHearts){
        setNumRows(numRows);
        setNumCols(numCols);
        setNumHearts(numHearts);
    }

    public void setNumRows(int numRows){
        this.numRows = numRows;
    }

    public void setNumCols(int numCols){
        this.numCols = numCols;
    }

    public void setNumHearts(int numHearts){
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
