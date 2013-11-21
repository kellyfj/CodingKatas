package com.kellyfj.codingkata.threads;

import java.util.concurrent.atomic.AtomicInteger;

public final class SingletonInstantiatedByClassloader {
    private static volatile AtomicInteger counter;
    private static SingletonInstantiatedByClassloader instance = new SingletonInstantiatedByClassloader();
    
    private SingletonInstantiatedByClassloader() {
        counter = new AtomicInteger(0);
    }
    
    public static SingletonInstantiatedByClassloader getInstance() {
        return instance;
    }
    
    public int getValue() {
       return counter.get(); 
    }
    
    public int increment() {
        return counter.incrementAndGet();
    }
}


