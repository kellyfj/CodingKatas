package com.kellyfj.codingkata.arrays;

import junit.framework.TestCase;

public class NumberArraysTest extends TestCase {

    public void testDoesSumOfTwoEqualN_Error() {
        int[] testArray = { 1 };
        try {
            NumberArrays.doesSumOfTwoEqualN(testArray, 10);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
        }       
    }
    
    public void testDoesSumOfTwoEqualN_False() {  
        int[] testArray = {1,2,3,5,6};
        boolean res = NumberArrays.doesSumOfTwoEqualN(testArray, 10);
        assertFalse(res);    
    }
    
    public void testDoesSumOfTwoEqualNTrue() {
        int[] testArray = {1,2,3,5,5};
        boolean res = NumberArrays.doesSumOfTwoEqualN(testArray, 10);
        assertTrue(res);
    }

    
    public void testDoesSumOfTwoEqualNTrue_unsorted() {
        int[] testArray = {5,4,3,2,1,9};
        boolean res = NumberArrays.doesSumOfTwoEqualN(testArray, 10);
        assertTrue(res);
    }
    
    public void test_doesSumOfThreeEqualN() {
        int[] testArray = {5,4,3,2,1,9};
        boolean res = NumberArrays.doesSumOfThreeEqualN(testArray, 10);
        assertTrue(res);
    }
    
    public void test_doesSumOfThreeEqualN_false() {
        int[] testArray = {1,2,3};
        boolean res = NumberArrays.doesSumOfThreeEqualN(testArray, 10);
        assertFalse(res);
    }
    
    public void test_doesSumOfThreeEqualN_error() {
        int[] testArray = {1,2};
        try{ 
            NumberArrays.doesSumOfThreeEqualN(testArray, 10);
            fail("Exception expected");
        }
        catch(IllegalArgumentException expected) {
        }

    }    
}
