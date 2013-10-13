package com.kellyfj.codingkata.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    
    
    @Test
    public void testRegex() {
        assertTrue(StringUtils.isMatch(".*","aa"));
        
        assertFalse(StringUtils.isMatch("a","aa"));
        assertTrue(StringUtils.isMatch("aa", "aa"));
        assertFalse(StringUtils.isMatch("aa","aaa"));
        assertTrue(StringUtils.isMatch("a*","aa"));

        assertTrue(StringUtils.isMatch(".*","ab"));
        assertTrue(StringUtils.isMatch("a*b","aab"));
        /**
         *  | abc            | abc              | accept |
            | *              | abc              | accept |
            | *abc           | abc              | accept |
            | *abc           | aaabbbabc        | accept |
            | a*bc           | aaabbbabc        | accept |
            | a*bc           | abc              | accept |
            | a*             | abc              | accept |
            | a*             | a                | accept |
            | a*             | aa               | accept |
            | a*             | abcdef           | accept |
            | *abc*          | abc              | accept |
            | *****          | abc              | accept |
            | É              | abc              | accept |
            | .*             | abc              | accept |
            | .bc*           | abc              | accept |
            | .b*c*a         | abca             | accept |
            | *              | /EMPTY STRING/   | accept |
            | abc            | abcd             | reject |
            | *a             | abcd             | reject |
            | a              | /EMPTY STRING/   | reject |
            | .a*c           | abc              | reject |
            | a.*b           | abc              | reject |
            | ..             | abc              | reject |
            | /EMPTY STRING/ | /EMPTY STRING/   | reject |
            | /EMPTY STRING/ | abc              | reject |
         */
    }
}
