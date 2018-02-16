package com.sosy.qbielka.loveseeker;

/**
 * Singleton for board options
 * Created by Quince Bielka on 2018-02-15.
 */

class Options {
    private static Options currentOptions = null;
    private static int NUM_HEARTS = 6;
    private static int NUM_ROWS = 6;
    private static int NUM_COLS = 4;

    public static Options getInstance() {
        if(currentOptions == null){
            currentOptions = new Options();
        }
        return currentOptions;
    }

    public static void setRowsAndCols(int rows, int cols){
        NUM_ROWS = rows;
        NUM_COLS = cols;
    }

    public static void setNumHearts(int hearts){
        NUM_HEARTS = hearts;
    }

    public static int getNumHearts(){
        return NUM_HEARTS;
    }

    public static int getNumRows(){
        return NUM_ROWS;
    }

    public static int getNumCols(){
        return NUM_COLS;
    }

    private Options() {
    }

}
