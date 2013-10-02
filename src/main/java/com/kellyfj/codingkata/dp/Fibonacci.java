package com.kellyfj.codingkata.dp;

public class Fibonacci {

    public static String getFibUpTo(int max)
    {
        int last=0;
        int current = 1;
        StringBuffer sb = new StringBuffer(last+","+current+",");
        
        while(current < max)
        {
           int next = current + last;
           if(next < max)
               sb.append(next+",");
           
           last = current;
           current = next;          
        }
        
        return sb.toString();        
    }
    
    
    public static int fibonacci(int n)  {
        if(n == 0)
            return 0;
        else if(n == 1)
          return 1;
       else
          return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
