package com.kellyfj.codingkata.roman;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomanNumeralUtilsTest {

    @Test
    public void testGetRomanNumerals() {
        String r = RomanNumeralUtils.getRomanNumerals(1978);
        assertEquals("MCMLXXVIII",r);
    }
    
    @Test
    public void testGetInt() {
        int i = RomanNumeralUtils.getInt("MCMLXXVIII");
        assertEquals(1978,i);
    }

}
