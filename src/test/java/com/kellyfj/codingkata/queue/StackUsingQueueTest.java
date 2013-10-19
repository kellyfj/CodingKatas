package com.kellyfj.codingkata.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackUsingQueueTest {

       @Test
       public void testA() {
           StackUsingQueue s = new StackUsingQueue();
           s.push(1);
           s.push(2);
           s.push(3);
           
           assertEquals(3,s.pop());
           assertEquals(2,s.pop());
           assertEquals(1,s.pop());
           assertEquals(0,s.pop());
           assertEquals(0,s.pop());
           assertEquals(0,s.pop());
           assertEquals(0,s.pop());
           
       }
}
