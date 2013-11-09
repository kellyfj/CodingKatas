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

    public void testSSEncoding() {
        int i = RomanNumeralUtils.getSSColumnID("A");       
        assertEquals(1,i);

        i = RomanNumeralUtils.getSSColumnID("Z");       
        assertEquals(26,i);

        
        i = RomanNumeralUtils.getSSColumnID("AA");       
        assertEquals(26*1+1,i);
        
        i = RomanNumeralUtils.getSSColumnID("ZZ");       
        assertEquals(26*26+26,i);
    }
}
