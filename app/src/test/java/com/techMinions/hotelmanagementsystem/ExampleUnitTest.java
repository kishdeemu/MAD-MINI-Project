package com.techMinions.hotelmanagementsystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /** IT19043524 **/
    @Test
    public void roomtotPriceCalculation() {
        String input1 = "Single Room";
        int input2 = 2;
        int input3 = 3;
        int output;
        int expected = 63000;
        double delta = .1;

        roombooking rm = new roombooking();
        output = rm.roomtotPriceCalculation(input1, input2, input3);

        assertEquals(expected, output, delta);

    }

        /** IT19043456 **/
    @Test
    public void tabletotPriceCalculation() {
        String input1 = "For 2 people";
        int input2 = 2;
        int output;
        int expected = 4000;
        double delta = .1;

        tables tb = new tables();
        output = tb.totPriceCalculation(input1, input2);

        assertEquals(expected, output, delta);
    }




}