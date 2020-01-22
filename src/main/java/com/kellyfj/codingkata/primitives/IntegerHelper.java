package com.kellyfj.codingkata.primitives;

public class IntegerHelper {

    /**
     * EPIJ: 4.8 Reverse Digits
     * 
     * Time Complexity: O(n) where n is the number of digits
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
    
    /**
     * EPIJ: 4.11 Rectangle Intersection
     */
    public static class Rectangle {
        int x, y, width, height;

        public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
    
    public static boolean IsIntersect(Rectangle r1, Rectangle r2) {
        boolean xRangesOverlap = r1.x <= r2.x + r2.width && r1.x + r1.width >= r2.x;
        boolean yRangesOverlap = r1.y <= r2.y + r2.height && r1.y + r1.height >= r2.y;

        return xRangesOverlap && yRangesOverlap;
    }
}
