package com.sosy.qbielka.loveseeker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardUnitTests{
    @Test
    public void makeHeartsWorks() throws Exception {
        int sum = 0;
        Board testMe = new Board(16,4,4);
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                if(testMe.getTile(row,col) == Tile.HEART){
                    sum++;
                }
            }
        }
        assertEquals (sum, 16);
        scanWorks();
    }

    public void scanWorks() throws Exception {
        Board testMe = new Board(0,4,4);
        testMe.board[1][1] = Tile.HEART;
        int one = testMe.scan(1,2);
        assertEquals(one, 1);
    }


}