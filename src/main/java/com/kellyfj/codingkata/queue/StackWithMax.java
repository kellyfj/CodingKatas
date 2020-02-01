package com.kellyfj.codingkata.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * EPIJ 8.1 The simplest way to implement a max operation is to check each element in the stack by iterating through 
 * the underlying array. Time Complexity: O(n) and space complexity: O(1) 
 * Time complexity can be reduced to O(log n) via the use of auxiliary data structures e.g. heap / BST and a hash
 * table. But the space complexity increases to O(n) and the code becomes complex. We could use a single extra variable 
 * "max" to record the max element. Updating max on pushes is easy but updating max on a pop is time consuming if "max" 
 * is the value being popped as we need to scan all remaining elements. We can trade time for space and cache the 
 * maximum stored at or below every entry - then when we pop we evict the corresponding cache value.
 * Each of these methods has Time Complexity: O(1) Space Complexity: O(n)
 */
public class StackWithMax {
	private static class ElementWithCachedMax {
		public Integer element;
		public Integer maxLowerOnTheStack;

		public ElementWithCachedMax(Integer e, Integer m) {
			this.element = e;
			this.maxLowerOnTheStack = m;
		}
	}

	public static class Stack {
		private Deque<ElementWithCachedMax> elementWithCachedMax = new ArrayDeque<>();

		public boolean isEmpty() {
			return elementWithCachedMax.isEmpty();
		}

		public Integer max() {
			if (isEmpty()) {
				throw new IllegalStateException("Stack is empty");
			}
			return elementWithCachedMax.peek().maxLowerOnTheStack;
		}

		public Integer pop() {
			if (isEmpty()) {
				throw new IllegalStateException("Stack is empty");
			}
			return elementWithCachedMax.removeFirst().element;
		}

		public void push(Integer x) {
			Integer currMax = isEmpty() ? x : max();
			Integer newMax = Math.max(x, Math.max(x, currMax));
			elementWithCachedMax.addFirst(new ElementWithCachedMax(x, newMax));
		}
	}
	
	/**
	 * We can improve on the best-case space needed by observing that if an element being pushed
	 * is smaller than the max element already in the stack then that element can never be the maximum
	 * so no need to record it. We cannot store the sequence of max values in a separate stack because of 
	 * the possibility of duplicates so we record the number of occurrences of each max value.
	 */
	private static class MaxWithCount {
		public Integer max; 
		public Integer count;
		
		public MaxWithCount(Integer max, Integer count) {
			this.max = max;
			this.count = count;
		}
	}
	
	public static class ImprovedStack {
		private Deque<Integer> element = new ArrayDeque<>();
		private Deque<MaxWithCount> cachedMaxWithCount = new ArrayDeque<>();
		
		public boolean isEmpty() { return element.isEmpty(); }
		
		public Integer max() {
			if(this.isEmpty()) {
				throw new IllegalStateException("max(): empty stack");
			}
			return cachedMaxWithCount.peekFirst().max;
		}
		
		public Integer pop() {
			if(this.isEmpty()) {
				throw new IllegalStateException("pop(): empty stack");
			}
			Integer popElement = element.removeFirst();
			if(popElement.equals(cachedMaxWithCount.peekFirst().max)) {
				cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count - 1;
				if(cachedMaxWithCount.peekFirst().count.equals(0)) {
					cachedMaxWithCount.removeFirst();
				}
			}	
			return popElement;
		}
		
		public void push(Integer x) {
			element.addFirst(x);
			if (!cachedMaxWithCount.isEmpty()) {
				if (Integer.compare(x, cachedMaxWithCount.peekFirst().max) == 0) {
					cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count + 1;
				} else if (Integer.compare(x, cachedMaxWithCount.peekFirst().max) > 0) {
					cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
				} 
			} else {
				cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
			}
		}
	}
}
