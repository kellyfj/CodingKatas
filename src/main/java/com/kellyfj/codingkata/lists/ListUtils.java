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
     * EPIJ 7.2: Reverse a single sublist within a list
     * Key - perform the the update in a single pass combining the identification with the reversal
     * 
     * Time Complexity: Dominated by search for fth node i.e. O(f)
     * Space Complexity: O(1)
     */
    public static ListElement reverseSublist(ListElement L, int start, int finish) {
    	ListElement dummyHead = new ListElement();
    	dummyHead.setNext(L);
    	ListElement a = dummyHead;
    	
    	int k = 1;
    	//Move one pointer to start of list
    	while(k++ < start) {
    		a = a.getNext();
    	}
    	
    	//Reverse as we go
    	ListElement b = a.getNext();
    	
    	// . . . A -> B -> C -> D  . . . .
    	while(start++ < finish) {
    		ListElement c = b.getNext();
    		b.setNext(c.getNext()); 
    		c.setNext(a.getNext()); 
    		a.setNext(c);
    	}
    	
    	return dummyHead.getNext();
    }

    /**
     * EPIJ 7.3 Test for Cyclicity
     * 	Brute force solution uses a HashMap/HashSet and O(n) additional space.
     *  Brute force solution without additional storage is to use two for loops but is O(n^2)
     *  Can make it work with O(n) time and O(1) space by using two pointers - slow and fast.
     *  The list has a cycle if the two iterators meet.
     *  
     *  Time Complexity: ???
     *  Space Complexity: O(1)
     */   
    public static ListElement hasCycle(ListElement head) {
    	ListElement fast = head;
    	ListElement slow = head;
    	
    	while(fast != null && fast.getNext() != null) {
    		slow = slow.getNext();
    		fast = fast.getNext().getNext();
    		if(slow == fast) {
    			//There is a cycle
    			//Now try to find the start of the cycle
    			slow = head;
    			//Both pointers advance at the same time
    			while(slow != fast) {
    				slow = slow.getNext();
    				fast = fast.getNext();
    			}
    			return slow;
    		}
    	}	
    	return null;
    } 
    
	/**
	 * EPIJ 7.4: Test for overlapping lists - lists are cycle free
	 * 
	 * Given two singly linked lists there may be list nodes that are common to both
	 * Write a program that takes two such lists and determines if there is a node
	 * that is common to both. Brute force - use a HashSet - takes O(n) time and
	 * O(n) space.
	 * 
	 * Lists overlap if and only if both have the same tail node: once the lists
	 * converge at a node they cannot diverge at a later node. So checking for
	 * overlap amounts to finding the tail nodes for each list.
	 * 
	 * Time Complexity: O(n) Space Complexity: O(1)
	 */
	public static ListElement overlappingLists(ListElement l1, ListElement l2) {
		int l1length = ListUtils.getSize(l1);
		int l2length = ListUtils.getSize(l2);

		// Advances the longer list to get equal length lists
		if (l1length > l2length) {
			l1 = advanceListByK(l1length - l2length, l1);
		} else {
			l2 = advanceListByK(l2length - l1length, l2);
		}

		while (l1 != null && l2 != null && l1 != l2) {
			l1 = l1.getNext();
			l2 = l2.getNext();
		}
		return l1;
	}

	private static ListElement advanceListByK(int k, ListElement l) {
		while (k-- > 0) {
			l = l.getNext();
		}
		return l;
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
    
	/**
	 * EPIJ 7.8: Remove duplicates from a sorted list
	 * 
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	public static ListElement removeDuplicatesFromSortedList(ListElement l) {
		ListElement iter = l;

		while (iter != null) {
			ListElement next = iter.getNext();
			while (next != null && next.getValue() == iter.getValue()) {
				next = next.getNext();
			}
			iter.setNext(next);
			iter = next;
		}

		return l;
	}
    
    
    /**
     * Utility Methods
     */    
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
    
    public static String printList(ListElement l) {
        if (l == null) {
        	String ret = "Empty";
        	System.out.println(ret);
            return ret;
        }
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
        while (l != null) {
        	sb.append(l.getValue() + ", ");
            l = l.getNext();
        }
        sb.append("}");
        String str = sb.toString();
        System.out.println(str);
        return str;
        
    }
}
