package com.kellyfj.codingkata.heap;

import java.util.TreeSet;

public class FixedSizeHeap<E> {
    private final int capacity;
    private final TreeSet<Long> ts = new TreeSet<Long>();
    private final boolean isMinHeap;

    public FixedSizeHeap(final int capacity, boolean minHeap) {
        this.capacity = capacity;
        isMinHeap = minHeap;
    }

    public boolean add(final Long e) {
        // initialized with 0 or less than zero capacity
        if (capacity <= 0) {
            return false;
        }

        // keep adding until we fill the queue
        if (ts.size() < capacity) {
            return ts.add(e);
        }

        if (isMinHeap && e < ts.first()) { //For Min Heap
            ts.pollLast();
            return ts.add(e);
        }
        if (!isMinHeap && e > ts.last()) {  //For Max Heap
            ts.pollFirst();
            return ts.add(e);
        }
        return false;
    }
    
    public Long pollFirst() {
    	return ts.pollFirst();
    }
    
    public Long pollLast() {
    	return ts.pollLast();
    }
}