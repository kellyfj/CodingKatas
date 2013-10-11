package com.kellyfj.codingkata.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testReverse() {
       String testString = "abcdef";
       
       String rev = StringUtils.reverse(testString);
       
       assertEquals(rev,"fedcba");
    }

}
