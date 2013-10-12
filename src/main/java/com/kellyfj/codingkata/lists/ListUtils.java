package com.kellyfj.codingkata.lists;

public class ListUtils {

    public static ListElement reverseListConstantStorage(ListElement head) {
        return reverse(null, head);
    }

    private static ListElement reverse(ListElement previous, ListElement current) {

        ListElement newHead = null;
        if (current.getNext() != null) {
            newHead = reverse(current, current.getNext());
        } else {// end of the list
            newHead = current;
            newHead.setNext(previous);
        }

        current.setNext(previous);
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
