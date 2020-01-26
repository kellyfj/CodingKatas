package com.kellyfj.codingkata.lists;

import java.util.Stack;

public class ListUtils {

	/**
	 * EPIJ 7.1 Merge two sorted lists
	 * 
	 * Time Complexity: O(n+m)
	 * Space Complexity: O(1) reusing nodes
	 */
	public static ListElement mergeTwoSortedLists(ListElement l1, ListElement l2) {
		ListElement dummyHead = new ListElement();
		ListElement current = dummyHead;
		
		while(l1 != null && l2 != null) {
			if(l1.getValue() <= l2.getValue()) {
				current.setNext(l1);
				l1 = l1.getNext();
			} else {
				current.setNext(l2);
				l2 = l2.getNext();
			}
			current = current.getNext();
		}
		
		//Append the remaining nodes
		current.setNext((l1 != null) ? l1 : l2);
		return dummyHead.getNext();
	}
	
    public static ListElement reverseListConstantStorage(ListElement head) {
        return reverseRecursively(null, head);
    }

    private static ListElement reverseRecursively(ListElement previous, ListElement current) {
        ListElement newHead = null;
        if (current.getNext() != null) {
            newHead = reverseRecursively(current, current.getNext());
        } else {// end of the list
            newHead = current;
        }
        current.setNext(previous);
        return newHead;
    }

    public static ListElement reverseIteratively_orderNspace(ListElement head) {
        ListElement current = head;
        Stack<ListElement> s = new Stack<ListElement>();
        while (current != null) {
            s.push(current);
            current = current.getNext();
        }
        ListElement curr = s.pop();
        ListElement prev = null;
        ListElement newHead = curr;
        while (!s.isEmpty()) {
            prev = s.pop();
            curr.setNext(prev);
            curr = prev;
        }
        prev.setNext(null);
        return newHead;
    }
    
    public static ListElement reverseIteratively_order1space(ListElement head) {
        ListElement next = null;
        ListElement prev = null;
        while (head != null) {
            next = head.getNext();
            head.setNext(prev);
            prev = head;
            head = next;
        }
        head = prev;        
        return head;
    }    
    
    /**
     * EPIJ 7.7: Remove k'th last elememnt from a list
     * 
     * TIME COMPLEXITY: O(n) length of the list
     * SPACE COMPLEXITY: O(1)
     */
    public static ListElement removeKthLastElement(ListElement head, int k) {
        
        ListElement kthAheadPointer = head;
        for(int i=1; i<=k; i++) {
            kthAheadPointer = kthAheadPointer.getNext();
            if(kthAheadPointer==null)
                throw new IllegalArgumentException("List not long enough");
        }
        
        ListElement currPtr = head;
        ListElement prevPtr = null;
        do {
            kthAheadPointer = kthAheadPointer.getNext();
            prevPtr = currPtr;
            currPtr = currPtr.getNext();
        } while (kthAheadPointer !=null);
        
        //CurrPtr is now the kth element
        //PrevPtr to point to (k+1)th element
        prevPtr.setNext(currPtr.getNext());
        
        return currPtr;
    }

    public static int getSize(ListElement head) {
        if (head == null) {
            throw new IllegalArgumentException("Null arg");
        }
        int ret = 0;
        while (head != null) {
            ret++;

            head = head.getNext();
        }
        return ret;
    }
    
    public static void printList(ListElement l) {
        if (l == null) {
            System.out.println("Empty");
            return;
        }
        System.out.print("{");
        while (l != null) {
            System.out.print(l.getValue() + ", ");
            l = l.getNext();
        }
        System.out.println("}");
    }
}
