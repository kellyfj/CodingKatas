package com.kellyfj.codingkata.primitives;

public class IntegerHelper {


    /**
     * Time Complexity? Not sure O(2^n) where n is the number of digits
     * Space Complexity: String + StringBuilder + Result
     */
    public static long reverseBruteForce(long x) {
        String str = Long.toString(Math.abs(x)); //Account for negative later
        StringBuilder sb = new StringBuilder(str);
        String reverseString = sb.reverse().toString();
        long result = Long.parseLong(reverseString);
        return x < 0 ? -1 * result : result;
    }
    
    /**
     * Time Complexity: O(n)
     * Space Complexity: Just the result
     */
    public static long reverseOrderN(long x) {
        long result = 0;
        long xRemaining = Math.abs(x);
        
        while(xRemaining != 0) {
            long rightMostDigit = xRemaining % 10;
            result = result * 10 + rightMostDigit;
            xRemaining /=10; //Get next digit
        }
        
        return x < 0 ? -result : result;
    }
    
}
