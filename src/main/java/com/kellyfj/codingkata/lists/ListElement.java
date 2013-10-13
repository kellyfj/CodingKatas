package com.kellyfj.codingkata.lists;

import java.util.HashSet;
import java.util.Set;

public class ListElement {

    private int value;
    private ListElement next;

    public ListElement getNext() {
        return next;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public int size() {
        
        if(next==null)
            return 1;
        else
            return 1+next.size();
    }
    
   public boolean containsCycle() {
       Set<ListElement> s = new HashSet<ListElement>();
       ListElement curr = next;
       while(curr!=null) {
           if(s.contains(curr))
               return true;
           else
               s.add(curr);
           curr = curr.getNext();
       }
       return false;
   }
}
