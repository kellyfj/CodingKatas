package com.kellyfj.codingkata.lists;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ListUtilsTest {

    ListElement head;
    
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
    }
    @Test
    public void testReverseListConstantStorage() {
        ListUtils.printList(head);
        int size = head.size();
        
        ListElement newHead =  ListUtils.reverseListConstantStorage(head);
        
        ListUtils.printList(newHead);
        
        assertEquals(size,newHead.size());
    }

}
