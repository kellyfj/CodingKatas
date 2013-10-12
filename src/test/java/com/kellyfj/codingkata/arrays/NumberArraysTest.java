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
    
    /**
     * Shuffle
     */
    public void testShuffle() {
        Integer[] testArray = {5,4,3,2,1,9};
        int beforeCount = testArray.length;
        
        System.out.println("BEFORE");
        NumberArrays.printArray(testArray);
        NumberArrays.shuffle(testArray);
        assertTrue(beforeCount==testArray.length);
        System.out.println("AFTER");
        NumberArrays.printArray(testArray);
    }
    
    public void testLongestMonotonicSequenceNone() {
        int[] testArray = {5,4,3,2,1,9};      
        int ret =   NumberArrays.lengthOfLongestMonotonicSeries(testArray);
        assertEquals(1,ret);
    }

    public void testLongestMonotonicSequenceAll() {
        int[] testArray = {1,2,3,4,5};      
        int ret =  NumberArrays.lengthOfLongestMonotonicSeries(testArray);
       
        assertEquals(ret,testArray.length);
    }
    
    
    public void testRearrange() {       
        int[] testArray = {0,0,1,0,1,2,2,1,2};     
        NumberArrays.rearrangeArray(testArray);
       
        NumberArrays.printArray(testArray);
    }
    
    public void testCountBinaryOnes() {
        assertEquals(1, NumberArrays.countBinaryOnes(2));
        assertEquals(2, NumberArrays.countBinaryOnes(3));
        assertEquals(1, NumberArrays.countBinaryOnes(4));
        assertEquals(2, NumberArrays.countBinaryOnes(5));
        assertEquals(3, NumberArrays.countBinaryOnes(7));
        
        
        
    }
}
