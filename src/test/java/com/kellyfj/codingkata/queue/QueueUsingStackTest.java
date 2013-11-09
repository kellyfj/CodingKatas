package com.kellyfj.codingkata.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QueueUsingStackTest {

    @Test
    public void test() {
        QueueUsingStack q = new QueueUsingStack();
        
        q.add("1");
        q.add("2");
        q.add("3");
        
        assertEquals("1",q.remove());
        assertEquals("2",q.remove());
        assertEquals("3",q.remove());
    }

}
