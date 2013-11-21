package com.kellyfj.codingkata.threads;

public class SingletonClassic {
    private int count;
    //volatile did not really work pre Java 5
    private static SingletonClassic instance;
    
    private SingletonClassic() {
        count =0;
    }
    
    public static SingletonClassic getInstance() {
        if(instance==null) {
            instance = new SingletonClassic();
        }
        return instance;
    }
    
    public int getValue() {
        return count; 
     }
     
     public synchronized int incrementSynchronized() {
         return count++;
     }  
}
