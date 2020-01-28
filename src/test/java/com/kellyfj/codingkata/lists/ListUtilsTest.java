package com.kellyfj.codingkata.lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
    public void testMergeTwoSortedListsDisjointSets() {
    	ListElement l1 = createListInRange(0, 10);
    	System.out.println("l1: " + l1.size());
    	ListElement l2 = createListInRange(11, 19);
    	System.out.println("l2: " + l2.size());
    	int expectedSize = l1.size() + l2.size();
    	
    	ListElement l3 = ListUtils.mergeTwoSortedLists(l1, l2);
    	ListUtils.printList(l3);    	
    	assertEquals(expectedSize, l3.size());	
    }
    
    @Test
    public void testMergeTwoSortedListsOverlappingSets() {
    	ListElement l1 = createListInRange(0, 10);
    	System.out.println("l1: " + l1.size());
    	ListElement l2 = createListInRange(1, 120);
    	System.out.println("l2: " + l2.size());
    	int expectedSize = l1.size() + l2.size();
    	
    	ListElement l3 = ListUtils.mergeTwoSortedLists(l1, l2);
    	ListUtils.printList(l3);    	
    	assertEquals(expectedSize, l3.size());	
    }
    
    private ListElement createListInRange(int start, int end) {
    	ListElement listHead = new ListElement();
    	listHead.setValue(start);

    	ListElement curr = listHead;
        for(int i=start+1; i<= end; i++)
        {
        	ListElement next = new ListElement();
            next.setValue(i);
            curr.setNext(next);
            curr = next;
        }
        
        return listHead;
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
    
    @Test
    public void testReverseSubList() {
    	ListElement head = createListInRange(1, 20);
    	ListElement newHead = ListUtils.reverseSublist(head, 5, 15);
    	String s = ListUtils.printList(newHead);
    	assertEquals(20, ListUtils.getSize(newHead));
    	assertEquals("{1, 2, 3, 4, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 16, 17, 18, 19, 20, }", s); 
    }
    
	@Test
	public void testHasCycle() {
		ListElement head = createListInRange(1, 20);
		ListElement cycle = ListUtils.hasCycle(head);
		assertTrue(cycle == null);

		ListElement temp = head;
		for (int i = 0; i < 10; i++) {
			temp = temp.getNext();
			temp.setNext(head);
		}
		cycle = ListUtils.hasCycle(head);
		assertTrue(cycle != null && cycle.getValue() == 1);
	}

	@Test
	public void testOverlapping() {
		ListElement list1 = new ListElement(1);
		ListElement l2a = new ListElement(2);
		ListElement list2 = new ListElement(20);
		ListElement l3 = new ListElement(3);
		ListElement l4 = new ListElement(4);
		ListElement l5 = new ListElement(5);
		
		list1.setNext(l2a);
		
		list2.setNext(l2a);
		l2a.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);
		
		ListElement overlap = ListUtils.overlappingLists(list1, list2);
		assertNotNull(overlap);
		assertEquals(2, overlap.getValue());
	}
	
	@Test
	public void testRemoveDupes() {
		ListElement head = createListInRange(1, 20);
		ListElement newhead = ListUtils.removeDuplicatesFromSortedList(head);
		assertEquals(20, ListUtils.getSize(newhead));
		
		ListElement l1 = new ListElement(1);
		ListElement l2 = new ListElement(2);
		ListElement l3a = new ListElement(3);
		ListElement l3b = new ListElement(3);
		ListElement l3c = new ListElement(3);
		ListElement l4 = new ListElement(4);
		l1.setNext(l2);
		l2.setNext(l3a);
		l3a.setNext(l3b);
		l3b.setNext(l3c);
		l3c.setNext(l4);
		assertEquals(6, ListUtils.getSize(l1));
		newhead = ListUtils.removeDuplicatesFromSortedList(l1);
		assertEquals(4, ListUtils.getSize(newhead));	
	}
}
