package com.sosy.qbielka.loveseeker.model;

/**
 * Singleton for board options
 * Created by Quince Bielka on 2018-02-15.
 */

//Singleton class to store information

public class Options {
    private static Options currentOptions = null;
    private int numHearts = 6;
    private int numRows = 4;
    private int numCols = 6;

    //additional features: save high points and num games started:
    private int numGamesCounter = 1;
    private int NUM_HEART_OPTIONS = 4;
    private int NUM_BOARD_OPTIONS = 3;
    private int[][] highPoints = new int[NUM_HEART_OPTIONS][NUM_BOARD_OPTIONS];
    //additional features done here

    public static Options getInstance() {
        if(currentOptions == null){
            currentOptions = new Options();
        }
        return currentOptions;
    }

    //additional features setters:
    public void incrementNumGamesCounter() {
        numGamesCounter++;
    }

    public void resetNumGamesCounter() {
        numGamesCounter = 1;
    }

    public void setHighPoints(int heartConfig, int boardSizeConfig, int points) {
        highPoints[heartConfig][boardSizeConfig] = points;
    }

    public void resetHighPoints() {

        for (int i = 0; i < NUM_HEART_OPTIONS; i++) {
            for (int j = 0; j < NUM_BOARD_OPTIONS; j++) {
                highPoints[i][j] = 0;
            }
        }
    }
    //additional features done

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

    //additional features getters:
    public int getNumGamesCounter() { return numGamesCounter; }

    public int getHighPoints(int heartConfig, int boardSizeConfig) { return highPoints[heartConfig][boardSizeConfig]; }
    //additional features done

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
