package com.kellyfj.codingkata.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapExamples {

	/**
	 * EPIJ 10.1: Merge Sorted Files
	 * 
	 * Brute force: Concatenate and Sort = O(n log n) This does not make use of the
	 * fact that the individual sequences are already sorted. We can repeatedly pick
	 * the smallest element amongst the first element of each of the remaining parts
	 * of each of the sequences. If there are k input sequences both the extract-min
	 * and the insert take O(log k) time. 
	 * We can do the merge in O(n log k) time.
	 * Space complexity: O(n + k) = O(n)
	 */
	public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
		for(List<Integer> array : sortedArrays) {
			iters.add(array.iterator());
		}
		
		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(), 
				new Comparator<ArrayEntry>() {
					@Override
					public int compare(ArrayEntry o1, ArrayEntry o2) {
						return Integer.compare(o1.value, o2.value);
					}});
		//Bootstrap initial N items in heap
		for(int i=0; i< iters.size(); i++) {
			if(iters.get(i).hasNext()) {
				minHeap.add(new ArrayEntry(iters.get(i).next(), i));
			}
		}
		
		List<Integer> result = new ArrayList<>();
		while(!minHeap.isEmpty()) {
			ArrayEntry head = minHeap.poll();
			result.add(head.value);
			if(iters.get(head.arrayId).hasNext()) {
				minHeap.add(new ArrayEntry(iters.get(head.arrayId).next(), head.arrayId));
			}
		}
		return result;
	}
	
	private static class ArrayEntry {
		public Integer value;
		public Integer arrayId;
		public ArrayEntry(Integer value, Integer arrayId) {
			this.value = value;
			this.arrayId = arrayId;
		}
	}
	
	/**
	 * EPIJ 10.3: Sort an almost-sorted array where each number is at most k away
	 * from its correctly sorted position. To solve this problem in a general
	 * setting we need to be able to store k+1 numbers and want to be able to
	 * efficiently extract the min number and add a new number. Use a min-heap. We
	 * add the first k numbers to a min-heap. Now we add additional numbers and
	 * extract the minimum from the heap. When the numbers run our we drain the heap.
	 * Time Complexity: O(n log k)
	 * Space Complexity: O(k)
	 */
	public static List<Integer> sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
		List<Integer> result = new ArrayList<>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		// Bootstrap the first k elements into the minheap
		for (int i = 0; i < k && sequence.hasNext(); i++) {
			minHeap.add(sequence.next());
		}

		while (sequence.hasNext()) {
			minHeap.add(sequence.next());
			Integer smallest = minHeap.remove();
			result.add(smallest);
		}

		while (!minHeap.isEmpty()) {
			result.add(minHeap.remove());
		}

		return result;
	}
	
	/**
	 * EPIJ 10.4: Compute k Closest stars. Use a Max-heap - always removing the max
	 * as we go.
	 * Time Complexity: O(n log k)
	 * Space Complexity: O(k)
	 */
	public static List<Star> findClosestKStars(List<Star> stars, int k) {
		// maxHeap to store the closest k stars so far
		PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
		for (Star s : stars) {
			maxHeap.add(s);
			if (maxHeap.size() == k + 1) {
				maxHeap.remove(); // remove the largest
			}
		}

		List<Star> result = new ArrayList<>(maxHeap);
		// The only guarantee PriorityQueue makes about ordering is that the maximum
		// element comes first so we have to sort what's left
		Collections.sort(result);
		return result;
	}

	public static class Star implements Comparable<Star> {
		private double x, y, z;

		public Star(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public double distance() {
			return Math.sqrt(x * x + y * y + z * z);
		}

		@Override
		public int compareTo(Star rhs) {
			return Double.compare(this.distance(), rhs.distance());
		}
	}
	
	/**
	 * EPIJ 10.5: Compute the Median of online data.
	 * The median of collection divides the collection into two equal parts. When a new element is
	 * added to the collection the parts can change by at most one element and the element to be moved
	 * is the largest of the smaller half or the smallest of the larger half.
	 * We can use two heaps, a max-heap for the smaller half and a min-heap for the larger half. We will keep
	 * the heaps balanced in size.
	 * Time Complexity: O(log n) per entry corresponding to insertion and extraction from heap
	 */
	public static List<Double> onlineMedian(List<Long> sequence) {
		// minHeap stores the larger half seen so far
		PriorityQueue<Long> minHeap = new PriorityQueue<>();
		// maxHeap stores the smaller half seen so far
		PriorityQueue<Long> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());
		List<Double> result = new ArrayList<>();

		for (Long x : sequence) {
			minHeap.add(x);
			maxHeap.add(minHeap.remove());
			// Ensure minHeap and maxHeap have equal number of elements if an even number of
			// elements is read
			// otherwise minHeap must have one more element than maxHeap
			if (maxHeap.size() > minHeap.size()) {
				minHeap.add(maxHeap.remove());
			}
			double currMedian = minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + maxHeap.peek())
					: (double) minHeap.peek();

			result.add(currMedian);
		}
		return result;
	}

	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
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
		FixedSizeHeap min_heap = new  FixedSizeHeap(n,true);
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
		FixedSizeHeap max_heap = new  FixedSizeHeap(n,false);
		for(int i=0; i< s.length; i++) {
			max_heap.add(s[i]);
		}
		
		for(int j=0; j< n; j++) {
			ret[j] = max_heap.pollLast();
		}
		
		return ret;
	}
}
