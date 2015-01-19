package com.kellyfj.codingkata.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static int numHelperCalls;

    public static boolean isPalindromeEasy_OrderNSpace(String s1, String s2) {
        if(s1 == null || s2== null)
            throw new IllegalArgumentException("Say something cool!");
        if(s1.length() != s2.length())
            return false;
        
        String s1reversed = new StringBuilder(s1).reverse().toString();
        return s2.equals(s1reversed);  
    }

    public static boolean isPalindromeHard_Order1Space(String s1, String s2) {
        if(s1 == null || s2== null)
            throw new IllegalArgumentException("Say something cool!");
        if(s1.length() != s2.length())
            return false;
        
        for(int i=0; i<s1.length(); i++) {
            if(s1.charAt(i)!=s2.charAt((s2.length()-1)-i)) {
                return false;
            }               
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
    
    public static String reverseAllWords_OrderNExtraSpace(String s) {
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
               reverse(chars, startPointer, currPointer);
               startPointer=currPointer+1;
           }
       }
       
       //reverse last word
       reverse(chars, startPointer, chars.length);
       
       return new String(chars);
    }


    public static String reverseAllWords(String s) {
        char[] ch = s.toCharArray();
        reverseAllWords_Order1Space(ch);
        
        return new String (ch);        
    }
    public static void reverseAllWords_Order1Space(char[] c) {
        reverse(c);

        int currPointer=0;
        int startPointer=0;
        while(currPointer < c.length-1) {
            currPointer++;
            //if we see a space
            if(c[currPointer]== ' ') {
                //reverse bytes between startPointer and currPointer
                reverse(c, startPointer, currPointer);
                startPointer=currPointer+1;
            }
        }
        
        //reverse last word
        reverse(c, startPointer, c.length);
    }

    private static void reverse(char[] c) {
        reverse(c,0,c.length);
    }  

    private static void reverse(char[] ch, int from, int to) {
        int diff = to - from;
        for (int i = 0; i < diff / 2; i++) {
            char temp = ch[from + i];
            ch[from + i] = ch[(to - i) - 1];
            ch[(to - i) - 1] = temp;
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
    
    
    /**
     * Code courtesy of <a href="http://www.java2s.com/Code/Java/Data-Type/Returnsthelevenshteindistanceoftwostrings.htm">this source</a>
     * 
     * @param s
     * @param t
     * @return
     */
    public static int levenshteinDistance(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0)
            return tLen;
        
        if (tLen == 0)
            return sLen;

        int[] costsPrev = new int[sLen + 1]; // previous cost array, horiz.
        int[] costs = new int[sLen + 1];     // cost array, horizontally
        int[] tmpArr;                        // helper to swap arrays
        int sptr, tptr;                      // current s and t index
        int cost;                            // current cost value
        char tIndexChar;                     // char of t at tIndexth pos.

        for (sptr = 0; sptr <= sLen; sptr++)
            costsPrev[sptr] = sptr;

        for (tptr = 1; tptr <= tLen; tptr++) {
            tIndexChar = t.charAt(tptr - 1);
            costs[0] = tptr;

            for (sptr = 1; sptr <= sLen; sptr++) {
                cost = (s.charAt(sptr - 1) == tIndexChar) ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, to the
                // diagonally left and to the up +cost
                costs[sptr] = Math.min(Math.min(costs[sptr - 1] + 1, costsPrev[sptr] + 1),
                                         costsPrev[sptr - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            tmpArr = costsPrev;
            costsPrev = costs;
            costs = tmpArr;
        }

        // we just switched costArr and prevCostArr, so prevCostArr now actually
        // has the most recent cost counts
        return costsPrev[sLen];
    }
    
    public static Set<List<String>> findAnagrams(String[] sArray) {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // Create lookup table
        for (int i = 0; i < sArray.length; i++) {
            String s = sArray[i];
            String key = createKey(s);
            List<String> l = null;
            if (map.containsKey(key)) {
                l = map.get(key);
            } else {
                l = new ArrayList<String>();
            }
            l.add(s);
            map.put(key, l);
        }

        // Find anagrams from lookup table
        Set<List<String>> retVal = new HashSet<List<String>>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            if(list.size() >=2)
                retVal.add(list);
        }
            
        return retVal;
    }

    private static String createKey(String s) {
        char[] toBeSorted = s.toLowerCase().toCharArray();
        Arrays.sort(toBeSorted);
        String sNew = new String(toBeSorted);
        return sNew;
    }
    
    
    public static String countAndSay(int nTimes) {
        if (nTimes <= 0)
            return null;
        StringBuilder res = new StringBuilder("1");
        for (int t = 0; t < nTimes; t++) {
            StringBuilder temp = countCharacters(res);
            res = temp;
            System.out.println(res);
        }
        return res.toString();
    }

    private static StringBuilder countCharacters(StringBuilder res) {
        StringBuilder newSB = new StringBuilder();
        int count = 1;
        for (int i = 1; i < res.length(); ++i) {
            if (res.charAt(i) == res.charAt(i - 1)) {
                count++;
            } else {
                newSB.append(count);
                newSB.append(res.charAt(i - 1));
                count = 1; // reset
            }
        }
        newSB.append(count);
        newSB.append(res.charAt(res.length() - 1));
        return newSB;
    }
    
    public static  List<String> permutation(String str) {
        numHelperCalls=0;
        List<String> ret = new ArrayList<String>();
        permutation("", str,ret);
        System.out.println("String ["+str+"] has ["+ret.size()+"] permutations");
        return ret;
    }

    private static void permutation(String temp, String str, List<String> result) {
        numHelperCalls++;
        if (str.length() == 0) {
            System.out.println(temp);
            result.add(temp);
        }
        else {
            for (int i = 0; i < str.length(); i++) {
                String temp2 = temp + str.charAt(i);
                String remainder = str.substring(0, i) + str.substring(i + 1, str.length());
                permutation(temp2,remainder,result);
            }
        }
    }
   
    public static  List<String> powerSetOfCharacters(String str) {      
        List<String> ret = powerSetOfCharacters(str.toCharArray());
        System.out.println("String ["+str+"] has ["+ret.size()+"] permutations");
        return ret;
    }
    
    private static List<String> powerSetOfCharacters(char[] array) {
        List<String> returnList = new ArrayList<String>();
        if (array.length == 0) {
            returnList.add(new String());
            return returnList;
        }

        char head = array[0];
        char[] rest = new char[] {};
        if (array.length >= 2)
            rest = Arrays.copyOfRange(array, 1, array.length);

        for (String s : powerSetOfCharacters(rest)) {
            StringBuilder newSet = new StringBuilder();
            newSet.append(head);
            newSet.append(s);
            returnList.add(newSet.toString());
            returnList.add(s);
        }
        return returnList;
    }
    
    
    public static String removeDupeChars(String s) {
        Set<Character> set = new HashSet<Character>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String longestPalindromeSimple(String s) {
        if (s.length() == 0)
            return "";
        String longest = s.substring(0, 1); // a single char itself is a
                                            // palindrome
        for (int i = 0; i < s.length() - 1; i++) {
            String p1 = expandAroundCenter(s, i, i);
            if (p1.length() > longest.length())
                longest = p1;

            String p2 = expandAroundCenter(s, i, i + 1);
            if (p2.length() > longest.length())
                longest = p2;
        }
        return longest;
    }

    private static String expandAroundCenter(String s, int c1, int c2) {
        int n = s.length();
        char[] chArray = s.toCharArray();

        while (c1 >= 0 && c2 <= n - 1 && chArray[c1] == chArray[c2]) {
            c1--;
            c2++;
        }
        int l = c1 + 1;
        int r = c2;
        return s.substring(l, r);
    }
    
    /**
     * Implementation from
     * http://en.wikibooks.org/wiki/Algorithm_Implementation/
     * Strings/Longest_common_substring#Java Order Complexity:
     * http://en.wikipedia.org/wiki/Longest_common_substring_problem
     * 
     * TIME COMPLEXITY: O(nm) ??Not 100% 
     * SPACE COMPLEXITY: O(nm) due to String creation
     */
    public static String longestCommonSubstring(String s1, String s2) {

        String maxString="";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                String s = expandAround(s1, s2, i, j);
                if (s.length() > maxString.length()) {
                    maxString=s;
                }
            }
        }
        return maxString;
    }

    private static String expandAround(String s1, String s2, int i, int j) {
        int x = 0;
        while (s1.charAt(i + x) == s2.charAt(j + x)) {
            x++;
            if ((i + x) >= s1.length() || (j + x) >= s2.length())
                break;
        }
        return s1.substring(i,(i+x));
    }
    
    /**
     * Inspired by Dan Blumenthal 
     * http://dandreamsofcoding.com/2015/01/09/dissecting-an-interview-question-math-is-hard/
     * TIME COMPLEXITY: O(n)  SPACE COMPLEXITY: O(n)
     */
	public static String addNumberStrings(String a, String b) {
		//Null Checks
		if (a == null && b == null)
			throw new IllegalArgumentException("You can't add two nulls!");
		else if (a == null)
			return b;
		else if (b == null)
			return a;
		
		// Empty String checks
		String aTrim = a.trim();
		String bTrim = b.trim();
		if (aTrim.isEmpty()) return bTrim;
		if (bTrim.isEmpty()) return aTrim;
		int bLength = bTrim.length();
		int aLength = aTrim.length();
		
		// Note we will build the String answer in reverse - fixing that at the end
		int max_answer_len = (aLength > bLength ? aLength : bLength) + 1;
		StringBuilder answer = new StringBuilder(max_answer_len);
		int idx = 0, carry = 0;
		// Start at the end of each string and move to the 'left'
		while (idx < aLength || idx < bLength) {
			char ch_b = idx >= bLength ? '0' : bTrim.charAt((bLength - 1) - idx);
			char ch_a = idx >= aLength ? '0' : aTrim.charAt((aLength - 1) - idx);
			int result = (ch_b - '0') + (ch_a - '0') + carry;
			carry = result / 10;
			answer.append(result % 10);
			idx++;
		}
		
		if (carry > 0)
			answer.append(carry);

		return answer.reverse().toString();
	}
    
    /**
     * Solution from Dan Blumental (with String trim added by me)
     * http://dandreamsofcoding.com/2015/01/09/dissecting-an-interview-question-math-is-hard-part-2/
     * 
     * @param a
     * @param b
     * @return
     */
    public static String addStringsByDan(String a, String b) {
    	//TODO Null Checks
    	  int carry = 0; // we start without a carry
    	  a = a.trim();
    	  b = b.trim();
      	//TODO Empty Checks
    	  int alen = a.length();
    	  int blen = b.length();
    	  int max_answer_len = (alen > blen ? alen : blen) + 1;
    	  StringBuilder answer = new StringBuilder(max_answer_len);
    	  for (int i = 0; i < alen || i < blen || carry > 0; i++) {
    	    int a_value = i >= alen ? 0 : (a.charAt(alen - i - 1) - '0');
    	    int b_value = i >= blen ? 0 : (b.charAt(blen - i - 1) - '0');
    	    int temp = a_value + b_value + carry;
    	    answer.append(temp % 10);
    	    carry = temp / 10;
    	  }
    	  return answer.reverse().toString();
    	}
}
