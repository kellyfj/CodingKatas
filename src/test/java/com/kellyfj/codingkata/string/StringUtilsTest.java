package com.kellyfj.codingkata.string;

import static org.apache.commons.lang3.StringUtils.getLevenshteinDistance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.ArithmeticUtils;
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
    public void testReverseWords_String() {
        String test = "The quick brown fox jumped over the lazy dog";
        String ret = StringUtils.reverseAllWords(test);
        assertEquals("dog lazy the over jumped fox brown quick The", ret);
    }
    
    @Test
    public void testReverseWords_char() {
        String test = "The quick brown fox jumped over the lazy dog";
        char[] ch = test.toCharArray();
        StringUtils.reverseAllWords_Order1Space(ch);
        assertEquals("dog lazy the over jumped fox brown quick The", new String(ch));
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
        
        @Test
        public void testPalindrome(){
            String s = "able was I ere I saw elba";
            boolean b = StringUtils.isPalindromeEasy_OrderNSpace(s, s);
            
            assertTrue(b);
        }
        @Test
        public void testPalindromeFalse(){
            String s = "able was I ere I saw elba";
            boolean b = StringUtils.isPalindromeEasy_OrderNSpace(s, s+" ");
            
            assertFalse(b);
        }
        @Test
        public void testPalindromeHard(){
            String s = "able was I ere I saw elba";
            boolean b = StringUtils.isPalindromeHard_Order1Space(s, s);
            
            assertTrue(b);
        }
        @Test
        public void testPalindromeHardFalse(){
            String s = "able was I ere I saw elba";
            boolean b = StringUtils.isPalindromeHard_Order1Space(s, s+" ");
            
            assertFalse(b);
        }      

        @Test
        public void testGetLongestPalindrome() {
            String s = "able was I ere I saw elba";
            String longestPdrome = StringUtils.longestPalindromeSimple(s);
            
            assertEquals(s,longestPdrome);             
        }
        
        @Test
        public void tetGetLongestPalindrome2() {
            String s = "AZabbaZY";
            String longestPdrome = StringUtils.longestPalindromeSimple(s);
            
            assertEquals("ZabbaZ",longestPdrome); 
        }
        
        @Test
        public void tetGetLongestPalindromeFail() {
            String s = "ABCDEFGHI";
            String longestPdrome = StringUtils.longestPalindromeSimple(s);
            
            assertEquals("A",longestPdrome); 
        }
        
        
        @Test
        public void testVonLevenshteinDistance() {
            String s1 = "abc";
            String s2 = "abcd";
            
           int i = StringUtils.levenshteinDistance(s1, s2);
           int j = getLevenshteinDistance(s1, s2);
           assertEquals(j,i);          
        }
        
        @Test
        public void testVonLevenshteinDistance2() {
            String s1 = "abcdHello World";
            String s2 = "abcd";
            
           int i = StringUtils.levenshteinDistance(s1, s2);
           int j = getLevenshteinDistance(s1, s2);
           assertEquals(j,i);          
        }
        
        @SuppressWarnings("unchecked")
        @Test
        public void testAnagramsFinder() {
            
           Set<List<String>> ret =  StringUtils.findAnagrams(new String[] { "able","momma","ELBA"});
           assertNotNull(ret);
           assertEquals(1,ret.size());
           printSet(ret);
           Object[] listOfStringsArray = ret.toArray();
           
           List<String> ls = (List<String>) listOfStringsArray[0];
           assertEquals(ls.size(),2);     
        }

        @Test
        public void testAnagramsFinder_fail() {
            
           Set<List<String>> ret =  StringUtils.findAnagrams(new String[] { "able","momma","ELBAS"});
           assertNotNull(ret);
           assertEquals(0,ret.size());
           printSet(ret);   
        }
        
        @SuppressWarnings("unchecked")
        @Test
        public void testAnagramsFinderMultipleHits() {
            
           Set<List<String>> ret =  StringUtils.findAnagrams(new String[] { "able","aBlE","ELBA"});
           assertNotNull(ret);
           assertEquals(1,ret.size());
           printSet(ret);
           Object[] listOfStringsArray = ret.toArray();
           
           List<String> ls = (List<String>) listOfStringsArray[0];
           assertEquals(ls.size(),3);     
        }
    
        
        @SuppressWarnings("unchecked")
        @Test
        public void testAnagramsFinderMultipleHits2() {
            
           Set<List<String>> ret =  StringUtils.findAnagrams(new String[] { "I saw", "able","aBlE","ELBA", "Saw I"});
           assertNotNull(ret);
           assertEquals(2,ret.size());
           printSet(ret);
           Object[] listOfStringsArray = ret.toArray();
           
           List<String> ls = (List<String>) listOfStringsArray[0];
           assertEquals(2,ls.size()); //"I saw" and "Saw I"
           ls = (List<String>) listOfStringsArray[1];
           assertEquals(3,ls.size()); //"able" etc.
           
        }
        
        
        private void printSet(Set<List<String>> ret) {
            for(List<String> l : ret) {
                   System.out.print("{");
                   for(String s : l) {
                       System.out.print("'"+s+"' ");
                   }
                   System.out.println("}");
               }
        }
        
        @Test
        public void testCountAndSay() {
            StringUtils.countAndSay(10);
            //System.out.println(s);
        }
        
        
        @Test 
        public void testPermutations() {
            String testString = "ABCDEFG";
            List<String> list = StringUtils.permutation(testString);
            long fact = ArithmeticUtils.factorial(testString.length());
            assertEquals(fact,list.size());
            System.out.println("Permutation Helper was called : "+StringUtils.numHelperCalls +" times");
        }

        
        @Test 
        public void testPermPowerSet() {
            String testString = "ABCD";
            List<String> list = StringUtils.powerSetOfCharacters(testString);
            double toN = Math.pow(2,testString.length());
            assertEquals(list.size(),(int)toN);
        }
        
        @Test
        public void testRemoveDupeChars() {
            
            String ans = StringUtils.removeDupeChars("ABCDE");
            assertEquals("ABCDE",ans);
            
            ans = StringUtils.removeDupeChars("AABCDE");
            assertEquals("ABCDE",ans);

            ans = StringUtils.removeDupeChars("AABCDEEE");
            assertEquals("ABCDE",ans);

            ans = StringUtils.removeDupeChars("AAAAA");
            assertEquals("A",ans);
        }
        
        @Test
        public void testLongestCommonSubstring() {
            String s1 = "Hello this is Frank speaking";
            String s2 = "Hi I am new here and my name is Frank Kelly";
            
            String ans = StringUtils.longestCommonSubstring(s1, s2);
            assertEquals(" is Frank ",ans); //Note the spaces around it
            
        }
        
        @Test
        public void testLongestCommonSubstringFail() {
            String s1 = "Hello this is Frank speaking";
            String s2 = "";
            
            String ans = StringUtils.longestCommonSubstring(s1, s2);
            assertEquals("",ans);
        }
        
        @Test
        public void testLongestCommonSubstring2() {
            String s1 = "A AB ABC ABCDE ABCDEF";
            String s2 = "AB ABCDEF";
            
            String ans = StringUtils.longestCommonSubstring(s1, s2);
            assertEquals(" ABCDEF",ans); //Note the spaces around i          
        }
        
        @Test
        public void testLongestCommonSubstring_matchOnFirstLongest() {
            String s1 = "ABCDEF XXX 123456";
            String s2 = "ABCDEF YYY 123456";
            
            String ans = StringUtils.longestCommonSubstring(s1, s2);
            assertEquals("ABCDEF ",ans); //Note the spaces around i          
        }
        
        
        @Test
        public void testAddTwoStrings() {
        	String s1 = "1234";
        	String s2 = "567";
        			
        	String answer = StringUtils.addNumbers(s1, s2);
        	System.out.println(answer);
        	assertEquals("1801", answer);
        }
        
        @Test
        public void testAddTwoStringsWithSpaces() {
        	String s1 = "    1234";
        	String s2 = "567    ";
        			
        	String answer = StringUtils.addNumbers(s1, s2);
        	System.out.println(answer);
        	assertEquals("1801", answer);
        }
        
        @Test
        public void testAddTwoStringsWithZeros() {
        	String s1 = "000001234";
        	String s2 = "000567";
        			
        	String answer = StringUtils.addNumbers(s1, s2);
        	System.out.println(answer);
        	Integer i_answer = new Integer(answer);
        	assertEquals(new Integer(1801), i_answer);
        }
        
        
}
