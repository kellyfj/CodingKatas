package com.kellyfj.codingkata.queue;

import java.util.Stack;

public class QueueUsingStack  {

    Stack<Object> s1 = new Stack<Object>();
    Stack<Object> tmp = new Stack<Object>();
    
    public void add(Object o) {
        s1.push(o);
    }
    
    public Object remove() {
        while(!s1.isEmpty()) {
            tmp.push(s1.pop());
        }
        Object o = tmp.pop();
        while(!tmp.isEmpty()){
            s1.push(tmp.pop());
        }
        return o;
    }
    
    public Object remove2() {
        if(tmp.isEmpty()) {
            while(!s1.isEmpty()) {
                tmp.push(s1.pop());
            }
        }
        if(!tmp.isEmpty()) {
            Object ret = tmp.pop();
            return ret;
        }
        throw new IllegalStateException("Something bad happened");
    }   
}
