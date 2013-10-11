package com.kellyfj.codingkata.string;

public class StringUtils {

    public static String reverse(String s) {
        byte[] original = s.getBytes();
        
        byte[] reverse = new byte[original.length];
        
        for(int i=0; i< original.length; i++) {
            reverse[i] = original[original.length-i-1];
        }
        return new String(reverse);
    }
}
