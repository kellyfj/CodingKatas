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
        assertEquals(99, newHead.getValue());
    }
    
    @Test
    public void testReverseIteratively() {
        ListUtils.printList(head);
        int size = head.size();
        
        ListElement newHead =  ListUtils.reverseIteratively_orderNspace(head);
        ListUtils.printList(newHead);        
        assertEquals(size,newHead.size());
        assertEquals(99, newHead.getValue());
    }
  
    @Test
    public void testReverseIteratively_order1Space() {
        ListUtils.printList(head);
        int size = head.size();
        
        ListElement newHead = ListUtils.reverseIteratively_order1space(head);
        ListUtils.printList(newHead);        
        assertEquals(size,newHead.size());
        assertEquals(99, newHead.getValue());
    }
    
    @Test
    public void testCycleFalse() {
        System.out.println("Test Cycle");
        ListUtils.printList(head);
        assertFalse(head.containsCycle());
        assertTrue(headWithCycle.containsCycle());
    }
    
    @Test
    public void testRemoveKthElement() {
        int startSize = ListUtils.getSize(head);
        assertEquals(100,startSize);
        ListUtils.printList(head);
        ListElement removed = ListUtils.removeKthLastElement(head, 5);
        assertEquals(startSize-5, removed.getValue());
        int endSize = ListUtils.getSize(head);
        ListUtils.printList(head);
        assertEquals(99,endSize);      
    }

}
