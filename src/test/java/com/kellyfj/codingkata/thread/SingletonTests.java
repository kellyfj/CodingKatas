package com.kellyfj.codingkata.thread;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.kellyfj.codingkata.threads.SingletonClassic;
import com.kellyfj.codingkata.threads.SingletonEnum;
import com.kellyfj.codingkata.threads.SingletonInstantiatedByClassloader;
public class SingletonTests {

    private static final int NUM_THREADS = 100;
    public static final int NUM_INCREMENTS = 100;
    
    @Test
    public void testClassloaderSingleton() {
        SingletonInstantiatedByClassloader singleton = SingletonInstantiatedByClassloader.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable worker = new IncrementerThread(singleton);
            executor.execute(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        assertEquals(NUM_THREADS * NUM_INCREMENTS, singleton.getValue());
    }
    
    @Test
    public void testSingletonEnum() {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable worker = new IncrementerThread2(singleton);
            executor.execute(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        assertEquals(NUM_THREADS * NUM_INCREMENTS, singleton.getValue());
    }
    
    
    @Test
    public void testClassic() {
        SingletonClassic singleton = SingletonClassic.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable worker = new IncrementerThread3(singleton);
            executor.execute(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        assertEquals(NUM_THREADS * NUM_INCREMENTS, singleton.getValue());
    }
    
    public class IncrementerThread implements Runnable {
       
        private final SingletonInstantiatedByClassloader c;
        
        public IncrementerThread(SingletonInstantiatedByClassloader c) {
            this.c=c;
        }
        
        @Override
        public void run() {
            
            for(int i=0; i< NUM_INCREMENTS; i++) {
                int saw = c.getValue();
                int after = c.increment();
                if(after != saw+1)
                    System.out.println("Saw ("+saw+") Incremented to ("+after+")");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
    
    public class IncrementerThread2 implements Runnable {
        
        private final SingletonEnum c;
        
        public IncrementerThread2(SingletonEnum c) {
            this.c=c;
        }
        
        @Override
        public void run() {
            
            for(int i=0; i< NUM_INCREMENTS; i++) {
                int saw = c.getValue();
                int after = c.increment();
                if(after != saw+1)
                    System.out.println("Saw ("+saw+") Incremented to ("+after+")");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
    
    public class IncrementerThread3 implements Runnable {
        
        private final SingletonClassic c;
        
        public IncrementerThread3(SingletonClassic c) {
            this.c=c;
        }
        
        @Override
        public void run() {
            
            for(int i=0; i< NUM_INCREMENTS; i++) {
                int saw = c.getValue();
                int after = c.incrementSynchronized();
                if(after != saw+1)
                    System.out.println("Saw ("+saw+") Incremented to ("+after+")");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}
