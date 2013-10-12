package com.kellyfj.codingkata.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testReverseString() {
       String testString = "abcdef";
       
       String rev = StringUtils.reverse(testString);
       
       assertEquals(rev,"fedcba");
       StringBuilder sb = new StringBuilder(testString);
       assertEquals(rev, sb.reverse().toString());
    }
    
    @Test
    public void testRegEx() {
        String testString = "1abcdef2";
        
        StringUtils.regExTester(testString);
    }

    
    @Test 
    public void testReverseWords() {
        String test = "The quick brown fox jumped over the lazy dog";
        
        String ret = StringUtils.reverseAllWords(test);
        
        assertEquals("dog lazy the over jumped fox brown quick The",ret);
    }
    
    @Test 
    public void testReverseWordsWithoutSplit() {
        String test = "The quick brown fox jumped over the lazy dog";
        
        String ret = StringUtils.reverseAllWordsWithoutSplit(test);
        
        assertEquals("dog lazy the over jumped fox brown quick The",ret);
    }
}
