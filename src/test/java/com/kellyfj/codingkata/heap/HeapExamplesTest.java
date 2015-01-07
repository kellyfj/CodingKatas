package com.kellyfj.codingkata.heap;

import java.util.Random;

import junit.framework.TestCase;

public class HeapExamplesTest extends TestCase {

	public void testHeapFindNSmallest() {
		
		Long[] randList = new Long[1024*1024];
		Random rand = new Random();
		Long minimum = Long.MAX_VALUE;
		for(int i=0; i< randList.length; i++) {
			Long l = Math.abs(rand.nextLong()) % Integer.MAX_VALUE/128;
			randList[i] = l;
			if(l < minimum) {
				minimum = l;
			}
		}
		
		Long[] nSmallest = HeapExamples.findNSmallest(randList, 10);
		
		//Check first Long is the smallest
		assertEquals(minimum, nSmallest[0]);
		for(int i=0; i< nSmallest.length-1; i++) {
			assertTrue(nSmallest[i] < nSmallest[i+1]);
			System.out.println("Smallest N Heap["+i+"] = " + nSmallest[i]);
		}
		
			
	}
	
	public void testHeapFindNLargest() {
		
		Long[] randList = new Long[1024*1024];
		Random rand = new Random();
		Long maximum = Long.MIN_VALUE;
		for(int i=0; i< randList.length; i++) {
			Long l = Math.abs(rand.nextLong()) % Integer.MAX_VALUE/128;
			randList[i] = l;
			if(l > maximum) {
				maximum = l;
			}
		}
		
		Long[] nLargest = HeapExamples.findNLargest(randList, 10);
		
		//Check first Long is the smallest
		assertEquals(maximum, nLargest[0]);
		for(int i=0; i< nLargest.length-1; i++) {
			assertTrue(nLargest[i] > nLargest[i+1]);
			System.out.println("Largest N Heap["+i+"] = " + nLargest[i]);
		}
		
			
	}
}
