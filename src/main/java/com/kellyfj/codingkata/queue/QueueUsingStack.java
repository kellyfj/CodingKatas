package com.kellyfj.codingkata.queue;

import java.util.Stack;

public class QueueUsingStack  {

    Stack<Object> A = new Stack<Object>();
    Stack<Object> B = new Stack<Object>();
    
    public void add(Object o) {
        A.push(o);
    }
    
    public Object remove() {
        if(B.isEmpty()) {
            while(!A.isEmpty()) {
                B.push(A.pop());
            }
        }
        if(!B.isEmpty()) {
            Object ret = B.pop();
            return ret;
        }
        throw new IllegalStateException("Something bad happened");
    }   
}
