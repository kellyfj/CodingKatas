package com.kellyfj.codingkata.lists;

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
    
   
}
