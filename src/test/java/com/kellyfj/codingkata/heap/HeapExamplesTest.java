package com.kellyfj.codingkata.heap;

import java.util.Random;

import com.kellyfj.codingkata.heap.HeapExamples.Star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class HeapExamplesTest extends TestCase {

	private Long[] randList = new Long[1024*1024];
	private Long minimum = Long.MAX_VALUE;
	private Long maximum = Long.MIN_VALUE;
	
	@Override
	public void setUp() {
		
		Random rand = new Random();
		
		for(int i=0; i< randList.length; i++) {
			Long l = Math.abs(rand.nextLong()) % Integer.MAX_VALUE;
			randList[i] = l;
			if(l < minimum) {
				minimum = l;
			}
			if(l > maximum) {
				maximum = l;
			}
		}
	}
	
	public void testMergeSortedArrays() {		
		List<List<Integer>> listOfLists = new ArrayList<>(); 
		Random rand = new Random();
		for(int i=0; i< 10; i++) {			
			List<Integer> l = new ArrayList<>();
			for(int j=0; j< 1000; j++) {
				l.add(i*10+j);
			}
			listOfLists.add(l);			
		}
		
		List<Integer> mergedArray = HeapExamples.mergeSortedArrays(listOfLists);
		assertNotNull(mergedArray);
		assertEquals(10*1000, mergedArray.size());
		for(int i=0; i< mergedArray.size()-1; i++) {
			int a = mergedArray.get(i);
			int b = mergedArray.get(i+1);
			if(a > b) {
				fail(a + " gt " + b);
			}
		}
	}
	
	public void testKClosestStars() {
		Random rand = new Random();
		List<Star> list = new ArrayList<>();
		for(int i=0; i< 1000; i++) {
			Star s = new Star(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
			list.add(s);
		}
	
		List<Star> kClosest = HeapExamples.findClosestKStars(list, 10);
		for(Star s : kClosest) {
			System.out.println(s.distance());
		}
		//Double check by globally sorting the list and check the first 
		Collections.sort(list);
		for(int i=0; i< 10; i++) {
			assertEquals(kClosest.get(i), list.get(i));
		}
	}
	
	public void testOnlineMedian() {
		List<Long> list = new ArrayList<>();
		Random r = new Random();
		for(int i=0; i< 2000; i++) {
			long l = r.nextInt(100);
			list.add(l);
		}
		List<Double> median = HeapExamples.onlineMedian(list);
		
		for(int i=0; i< median.size(); i++) {
			System.out.println(median.get(i));
		}
		Double lastMedian = median.get(median.size()-1);
		assertTrue("Last Median is " + lastMedian, 45.0 <= lastMedian && lastMedian <= 55.0 );
	}

	public void testHeapFindNSmallest() {
		Long[] nSmallest = HeapExamples.findNSmallest(randList, 10);
		
		//Check first Long is the smallest
		assertEquals(minimum, nSmallest[0]);
		for(int i=0; i< nSmallest.length-1; i++) {
			assertTrue(nSmallest[i] < nSmallest[i+1]);
			System.out.println("Smallest N Heap["+i+"] = " + nSmallest[i]);
		}		
	}
	
	public void testHeapFindNLargest() {
		Long[] nLargest = HeapExamples.findNLargest(randList, 10);
		
		//Check first Long is the smallest
		assertEquals(maximum, nLargest[0]);
		for(int i=0; i< nLargest.length-1; i++) {
			assertTrue(nLargest[i] > nLargest[i+1]);
			System.out.println("Largest N Heap["+i+"] = " + nLargest[i]);
		}		
	}
	
	public void testHeapFindNSmallestFixedSizeHeap() {
		Long[] nSmallest = HeapExamples.findNSmallestFixedSizeHeap(randList, 10);
		
		//Check first Long is the smallest
		assertEquals(minimum, nSmallest[0]);
		for(int i=0; i< nSmallest.length-1; i++) {
			assertTrue(nSmallest[i] < nSmallest[i+1]);
			System.out.println("Smallest N Heap["+i+"] = " + nSmallest[i]);
		}		
	}
	
	public void testHeapFindNLargestFixedSizeHeap() {
		Long[] nLargest = HeapExamples.findNLargestFixedSizeHeap(randList, 10);
		
		//Check first Long is the smallest
		assertEquals(maximum, nLargest[0]);
		for(int i=0; i< nLargest.length-1; i++) {
			assertTrue(nLargest[i] > nLargest[i+1]);
			System.out.println("Largest N Heap["+i+"] = " + nLargest[i]);
		}		
	}
}
