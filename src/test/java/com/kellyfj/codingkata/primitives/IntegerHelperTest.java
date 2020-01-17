package com.kellyfj.codingkata.primitives;

import com.kellyfj.codingkata.primitives.IntegerHelper.Rectangle;

import junit.framework.TestCase;

public class IntegerHelperTest extends TestCase {

    public void testReverseBruteForce() {        
        long rev = IntegerHelper.reverseBruteForce(12345L);
        assertEquals(54321L, rev);
        
        rev = IntegerHelper.reverseBruteForce(-12345L);
        assertEquals(-54321L, rev);
        
        rev = IntegerHelper.reverseBruteForce(Long.MAX_VALUE);
        assertTrue(rev > 0);
        rev = IntegerHelper.reverseBruteForce(Long.MIN_VALUE + 1);
        assertTrue(rev < 0); 
        //Long.MIN_VALUE does not work as the Math.abs of it is still negative 
        // From docs for Math.abs()::
        // Note that if the argument is equal to the value of
        // {@link Long#MIN_VALUE}, the most negative representable
        // {@code long} value, the result is that same value, which
        // is negative.
    }
    
    public void testReverseOrderN() {        
        long rev = IntegerHelper.reverseOrderN(12345L);
        assertEquals(54321L, rev);
        
        rev = IntegerHelper.reverseOrderN(-12345L);
        assertEquals(-54321L, rev);
        
        rev = IntegerHelper.reverseOrderN(Long.MAX_VALUE);
        assertTrue(rev > 0);
        rev = IntegerHelper.reverseOrderN(Long.MIN_VALUE + 1);
        assertTrue(rev < 0);    
        //Same Note as above on Math.abs()
    }
    
    public void testRectangleOverlap() {
        // Complete overlap
        Rectangle r1 = new Rectangle(0, 0, 2, 5);
        Rectangle r2 = new Rectangle(0, 0, 1, 1);

        assertTrue(IntegerHelper.IsIntersect(r1, r2));

        // Partial Overlap
        r1 = new Rectangle(0, 0, 2, 5);
        r2 = new Rectangle(1, 1, 2, 2);
        assertTrue(IntegerHelper.IsIntersect(r1, r2));

        // No Overlap
        r1 = new Rectangle(0, 0, 2, 5);
        r2 = new Rectangle(10, 10, 2, 2);
        assertFalse(IntegerHelper.IsIntersect(r1, r2));
        
        //No overlap closer
        r1 = new Rectangle(0, 0, 2, 5);
        r2 = new Rectangle(3, 6, 1, 1);
        assertFalse(IntegerHelper.IsIntersect(r1, r2));
        
        //"Point" overlap
        r1 = new Rectangle(0, 0, 2, 5);
        r2 = new Rectangle(2, 5, 1, 1);
        assertTrue(IntegerHelper.IsIntersect(r1, r2));
    }
}
