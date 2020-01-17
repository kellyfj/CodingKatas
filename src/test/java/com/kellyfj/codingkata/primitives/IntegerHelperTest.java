package com.kellyfj.codingkata.primitives;

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
}
