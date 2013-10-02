package com.kellyfj.codingkata.roman;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeralUtils {

    public static int getInt(String number) {
        if (number.equals("")) return 0;
        if (number.startsWith("M")) return 1000 + getInt(number.substring(1));
        if (number.startsWith("CM")) return 900 + getInt(number.substring(2));
        if (number.startsWith("D")) return 500 + getInt(number.substring(1));
        if (number.startsWith("CD")) return 400 + getInt(number.substring(2));
        if (number.startsWith("C")) return 100 + getInt(number.substring(1));
        if (number.startsWith("XC")) return 90 + getInt(number.substring(2));
        if (number.startsWith("L")) return 50 + getInt(number.substring(1));
        if (number.startsWith("XL")) return 40 + getInt(number.substring(2));
        if (number.startsWith("X")) return 10 + getInt(number.substring(1));
        if (number.startsWith("IX")) return 9 + getInt(number.substring(2));
        if (number.startsWith("V")) return 5 + getInt(number.substring(1));
        if (number.startsWith("IV")) return 4 + getInt(number.substring(2));
        if (number.startsWith("I")) return 1 + getInt(number.substring(1));
        throw new RuntimeException("something bad happened");
    }
   
    
    public static String getRomanNumerals(int i) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String result = "";
        for (Map.Entry<String, Integer> entry : roman_numerals.entrySet()) {
            String romanNumeral = entry.getKey();
            int numericValue = entry.getValue();
            
            //Divide by biggest number
            int numMatches = i / numericValue;
            result += repeatCharacter(romanNumeral, numMatches);
            //Calculate remainder
            i = i % entry.getValue();
        }
        return result;
    }

    private static String repeatCharacter(String s, int n) {
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
