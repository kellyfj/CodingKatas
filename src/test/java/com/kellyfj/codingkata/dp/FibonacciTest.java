package com.kellyfj.codingkata.dp;

import junit.framework.TestCase;

public class FibonacciTest extends TestCase {


    public void testGetFibUpTo()
    {
        String s= Fibonacci.getFibUpTo(999);
        System.out.println(s);
    }

    
    public void testGetFib()
    {
        int i= Fibonacci.fibonacci(20);
        System.out.println(i+"");
    }
}
