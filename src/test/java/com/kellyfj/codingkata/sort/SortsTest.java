package com.kellyfj.codingkata.sort;

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
        
        int[] smallArray = new int[10000];
        for(int i=0; i< smallArray.length; i++)
        {
            smallArray[i] = (int)(Math.random()*10000);
        }
        Sorts.bubbleSort(smallArray);
        
        printArray(smallArray);
       
    }
    
    private void printArray(int[] a)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< a.length; i++)
        {
            sb.append(a[i]+",");
        }
        System.out.println(sb.toString());
    }

}
