package com.kellyfj.codingkata.threads;

import java.util.concurrent.atomic.AtomicInteger;

public enum SingletonEnum {  
        INSTANCE;
        
        private static volatile AtomicInteger count = new AtomicInteger(0);
        
        public int getValue() {
            return count.get(); 
         }
         
         public int increment() {
             return count.incrementAndGet();
         }
}
