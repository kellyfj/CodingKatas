package com.kellyfj.codingkata.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QueueUsingDequeTest {

    @Test
    public void test() {
    	QueueUsingDeque q = new QueueUsingDeque();
        
        q.add(1);
        q.add(2);
        q.add(3);
        
        assertEquals(1,q.remove().intValue());
        assertEquals(2,q.remove().intValue());
        assertEquals(3,q.remove().intValue());
        q.add(4);
        q.add(5);
        assertEquals(4,q.remove().intValue());
        q.add(6);
        assertEquals(5,q.remove().intValue());
        assertEquals(6,q.remove().intValue());        
    }

}

