package com.sosy.qbielka.loveseeker;

import com.sosy.qbielka.loveseeker.model.Options;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OptionsUnitTest {

    @Test
    public void RequestOptions() {
        Options test  = Options.getInstance();
    }

    @Test
    public void ChangeOptions() {
        Options test  = Options.getInstance();
        try {
            test.setCurrentOptions(9, 7, 8);
        }catch (Exception e){
            fail();
        }
        assertEquals( test.getNumRows(), 9);
        assertEquals( test.getNumCols(), 7);
        assertEquals( test.getNumHearts(), 8);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFail1() throws Exception{
        Options test  = Options.getInstance();
        test.setCurrentOptions(-1, 7, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFail2() throws Exception{
        Options test  = Options.getInstance();
        test.setCurrentOptions(1, -7, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFail3() throws Exception{
        Options test  = Options.getInstance();
        test.setCurrentOptions(1, 7, -8);
    }





}