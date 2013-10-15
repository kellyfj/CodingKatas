package com.kellyfj.codingkata.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isPalindromeEasy(String s1, String s2) {
        if(s1 == null || s2== null)
            throw new IllegalArgumentException("Say something cool!");
        if(s1.length() != s2.length())
            return false;
        
        String s1reversed = new StringBuilder(s1).reverse().toString();
        return s2.equals(s1reversed);  
    }

    public static boolean isPalindromeHard(String s1, String s2) {
        if(s1 == null || s2== null)
            throw new IllegalArgumentException("Say something cool!");
        if(s1.length() != s2.length())
            return false;
        
        char[] s1array = s1.toCharArray();
        char[] s2array = s2.toCharArray();
        
        for(int i=0; i<s1array.length; i++) {
            char ch1 = s1array[i];
            char ch2 = s2array[(s2array.length-1)-i];
            if(ch1!=ch2)
                return false;
            //else continue;
        }
        
        return true;
    }
    
    public static String reverse(String s) {
        byte[] original = s.getBytes();

        byte[] reverse = new byte[original.length];

        for (int i = 0; i < original.length; i++) {
            reverse[i] = original[original.length - i - 1];
        }
        return new String(reverse);
    }

    public static void regExTester(String s) {

        String regex = "[A-Za-z]";
        String replacement = "0";
        boolean b = s.matches(regex);
        if (b)
            System.out.println("String " + s + " matches regex " + regex);

        String s2 = s.replaceAll(regex, replacement);
        System.out.println("String.replaceAll() Before: " + s + " After:" + s2);

        String s3 = s.replaceFirst(regex, replacement);
        System.out.println("String.replaceFirst() Before: " + s + " After:" + s3);

        String[] strArray = s.split(regex);
        System.out.println("String.split() Created " + strArray.length + " strings");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println("Found the text '" + matcher.group() + "' starting at " + matcher.start()
                    + " and ending at index " + matcher.end());
        }
    }
    
    public static String reverseAllWords(String s) {
        if (s == null)
            throw new IllegalArgumentException("S cannot be null");
        String[] words = s.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i] + " ");
        }

        return sb.toString().trim();
    }
    
    
 
    public static String reverseAllWordsWithoutSplit(String s) {
        if (s == null)
            throw new IllegalArgumentException("S cannot be null");
       String reversedChars = new StringBuilder(s).reverse().toString();
       
       char[] chars = reversedChars.toCharArray();
       int currPointer=0;
       int startPointer=0;
       while(currPointer < chars.length-1) {
           currPointer++;
           //if we see a space
           if(chars[currPointer]== ' ') {
               //reverse bytes between startPointer and currPointer
               reversePartOfCharArray(chars, startPointer, currPointer);
               startPointer=currPointer+1;
           }
       }
       
       //reverse last word
       reversePartOfCharArray(chars, startPointer, chars.length);
       
       return new String(chars);
    }

    private static void reversePartOfCharArray(char[] ch, int from, int to) {
        int diff= to-from;
           for(int i=0; i<diff/2; i++) {
               char temp = ch[from+i];
               ch[from+i] = ch[(to-i)-1];
               ch[(to-i)-1] = temp;
           }
    }
    
    public static boolean isMatch(String regex, String s) {
        if (regex == null)
            throw new IllegalArgumentException("RegEx argument cannot be null");
        if (s == null)
            throw new IllegalArgumentException("Test string cannot be null");

        char[] regExArray = regex.toCharArray();
        char[] strArray = s.toCharArray();
        return isMatch(regExArray, 0, strArray, 0);
    }

    private static boolean isMatch(char[] pattern, int patInd, char[] text, int txtInd) {
        if (text.length == txtInd && pattern.length == patInd)
            return true;
        if (text.length == txtInd || pattern.length == patInd)
            return false;
        if (text[txtInd] == pattern[patInd] || pattern[patInd] == '?' || pattern[patInd] == '.')
            return isMatch(pattern, patInd + 1, text, txtInd + 1);
        if (pattern[patInd] == '*')
            return isMatch(pattern, patInd + 1, text, txtInd + 1) 
                    || isMatch(pattern, patInd, text, txtInd + 1)
                    || isMatch(pattern, patInd + 1, text, txtInd);
        else 
            return false;
    }  
     
}
