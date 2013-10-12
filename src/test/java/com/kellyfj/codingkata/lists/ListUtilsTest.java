package com.kellyfj.codingkata.lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ListUtilsTest {

    ListElement head;
    ListElement headWithCycle;
    
    @Before
    public void setUp() {
        head = new ListElement();
        head.setValue(0);
        ListElement curr = head;

        ListElement next = null;
        for(int i=1; i< 100; i++)
        {
            next = new ListElement();
            next.setValue(i);
            curr.setNext(next);
            curr = next;
        }
        
        headWithCycle = new ListElement();
        ListElement next1 = new ListElement();
        headWithCycle.setNext(next1);
        next1.setNext(headWithCycle);
    }
    
    @Test
    public void testReverseListConstantStorage() {
        ListUtils.printList(head);
        int size = head.size();
        
        ListElement newHead =  ListUtils.reverseListConstantStorage(head);
        ListUtils.printList(newHead);        
        assertEquals(size,newHead.size());
    }
    
    @Test
    public void testCycleFalse() {
        System.out.println("Test Cycle");
        ListUtils.printList(head);
        assertFalse(head.containsCycle());
        assertTrue(headWithCycle.containsCycle());
    }

}
