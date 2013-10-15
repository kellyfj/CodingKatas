package com.kellyfj.codingkata.lists;

import java.util.Stack;

public class ListUtils {

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

    public static ListElement reverseIteratively(ListElement head) {

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
