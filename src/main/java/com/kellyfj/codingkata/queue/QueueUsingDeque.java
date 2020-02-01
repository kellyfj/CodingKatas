package com.kellyfj.codingkata.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * EPIJ 8.8: Implement a Queue using Two Deques
 */
public class QueueUsingDeque {

	private Deque<Integer> enqueue = new ArrayDeque<>();
	private Deque<Integer> dequeue = new ArrayDeque<>();

	public void add(Integer x) {
		enqueue.addFirst(x);
	}

	public Integer remove() {
		if (dequeue.isEmpty()) {
			while (!enqueue.isEmpty()) {
				dequeue.addFirst(enqueue.removeFirst());
			}
		}

		if (!dequeue.isEmpty()) {
			return dequeue.removeFirst();
		}

		throw new IllegalStateException("Cannot pop empty queue");
	}
}
