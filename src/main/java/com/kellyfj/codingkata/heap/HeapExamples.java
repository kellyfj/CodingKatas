package com.kellyfj.codingkata.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class HeapExamples {

	
	/**
	 * Finds the N smallest but it's very space inefficient.
	 * Need a FixedSizePriorityQueue implementation that only keeps "n" objects.
	 */
	public static Long[] findNSmallest(Long[] s, int n) {
		Long[] ret = new Long[n];
		PriorityQueue<Long> min_heap = new  PriorityQueue<Long>(n);
		for(int i=0; i< s.length; i++) {
			min_heap.add(s[i]);
		}
		
		for(int j=0; j< n; j++) {
			ret[j] = min_heap.remove();
		}
		
		return ret;
	}
	
	public static Long[] findNLargest(Long[] s, int n) {
		Long[] ret = new Long[n];
		PriorityQueue<Long> max_heap = new  PriorityQueue<Long>(n, Collections.reverseOrder());
		for(int i=0; i< s.length; i++) {
			max_heap.add(s[i]);
		}
		
		for(int j=0; j< n; j++) {
			ret[j] = max_heap.remove();
		}
		
		return ret;
	}
	
	public static Long[] findNSmallestFixedSizeHeap(Long[] s, int n) {
		Long[] ret = new Long[n];
		FixedSizeHeap<Long> min_heap = new  FixedSizeHeap<Long>(n,true);
		for(int i=0; i< s.length; i++) {
			min_heap.add(s[i]);
		}
		
		for(int j=0; j< n; j++) {
			ret[j] = min_heap.pollFirst();
		}
		
		return ret;
	}
	
	public static Long[] findNLargestFixedSizeHeap(Long[] s, int n) {
		Long[] ret = new Long[n];
		FixedSizeHeap<Long> max_heap = new  FixedSizeHeap<Long>(n,false);
		for(int i=0; i< s.length; i++) {
			max_heap.add(s[i]);
		}
		
		for(int j=0; j< n; j++) {
			ret[j] = max_heap.pollLast();
		}
		
		return ret;
	}
}
