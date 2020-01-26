package com.kellyfj.codingkata.roman;

import junit.framework.TestCase;

public class RomanNumeralUtilsTest extends TestCase {

    public void testGetRomanNumerals() {
        String r = RomanNumeralUtils.getRomanNumerals(1978);
        assertEquals("MCMLXXVIII",r);
    }
    
    public void testGetInt() {
        int i = RomanNumeralUtils.getInt("MCMLXXVIII");
        assertEquals(1978,i);
    }

}
