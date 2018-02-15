package com.sosy.qbielka.loveseeker;

/**
 * Created by Quince Bielka on 2018-02-15.
 */

class Options {
    private static Options currentOptions = null;
    private static int NUM_HEARTS = 6;
    private static int NUM_ROWS = 6;
    private static int NUM_COLS = 4;

    static Options getInstance() {
        if(currentOptions == null){
            currentOptions = new Options();
        }
        return currentOptions;
    }

    static void setRowsAndCols(int rows, int cols){
        NUM_ROWS = rows;
        NUM_COLS = cols;
    }
    
    static void setNumHearts(int hearts){
        NUM_HEARTS = hearts;
    }

    private Options() {
    }

}
