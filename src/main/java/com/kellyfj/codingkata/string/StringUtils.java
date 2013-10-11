package com.kellyfj.codingkata.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

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
}
