package com.kellyfj.codingkata.sort;

import java.util.Arrays;

import junit.framework.TestCase;

public class SortsTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBubbleSort() {
        int[] smallArray = createTestArray();        
        long start = System.currentTimeMillis();
        int numPasses = Sorts.bubbleSort(smallArray);
        long end = System.currentTimeMillis();
        //printArray(smallArray);       
        System.out.println("Bubble Sorting took "+(end-start)+" ms in "+numPasses+" passes");
    }
  
    public void testSelectionSort() {
        int[] smallArray = createTestArray();
        long start = System.currentTimeMillis();
        Sorts.selectionSort(smallArray);
        long end = System.currentTimeMillis();
        //printArray(smallArray);
        System.out.println("Select Sorting took "+(end-start)+" ms ");
    }
    
    public void testMergeSort() {
        int[] smallArray = createTestArray();
        long start = System.currentTimeMillis();
        Sorts.mergeSort(smallArray);
        long end = System.currentTimeMillis();
        //printArray(smallArray);
        System.out.println("Merge Sorting took "+(end-start)+" ms ");
    }

    
    public void notestQuickSort() {
        int[] smallArray = createTestArray();
        long start = System.currentTimeMillis();
        Sorts.quickSort(smallArray);
        long end = System.currentTimeMillis();
        //printArray(smallArray);
        System.out.println("Quick Sorting took "+(end-start)+" ms ");
    }
    
    public void testArraysDotSort() {
        int[] smallArray = createTestArray();
        long start = System.currentTimeMillis();
        Arrays.sort(smallArray);
        long end = System.currentTimeMillis();
        //printArray(smallArray);
        System.out.println("Standard Java Arrays.sort() Sorting took "+(end-start)+" ms ");
    }
    
    private static final int[] testArray = new int[10000];
    private static boolean isInitialized = false;
    
    private int[] createTestArray() {
        
        if(isInitialized)
            return testArray;
        
        for(int i=0; i< testArray.length; i++) {
            testArray[i] = (int)(Math.random()*10000);
        }
        isInitialized = true;
        return testArray;
    }
    

}
