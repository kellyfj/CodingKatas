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
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable worker = new IncrementerThread();
            executor.execute(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        assertEquals(NUM_THREADS * NUM_INCREMENTS, SingletonInstantiatedByClassloader.getInstance().getValue());
    }
    
    @Test
    public void testSingletonEnum() {
       
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable worker = new IncrementerThread2();
            executor.execute(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        assertEquals(NUM_THREADS * NUM_INCREMENTS, SingletonEnum.INSTANCE.getValue());
    }
    
    
    @Test
    public void testClassic() {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < NUM_THREADS; i++) {
            Runnable worker = new IncrementerThread3();
            executor.execute(worker);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        assertEquals(NUM_THREADS * NUM_INCREMENTS, SingletonClassic.getInstance().getValue());
    }
    
    public class IncrementerThread implements Runnable {

        public IncrementerThread() {
        }
        
        @Override
        public void run() {
            
            for(int i=0; i< NUM_INCREMENTS; i++) {
                int saw = SingletonInstantiatedByClassloader.getInstance().getValue();
                int after = SingletonInstantiatedByClassloader.getInstance().increment();
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
        
        public IncrementerThread2() {
        }
        
        @Override
        public void run() {
            
            for(int i=0; i< NUM_INCREMENTS; i++) {
                int saw = SingletonEnum.INSTANCE.getValue();
                int after = SingletonEnum.INSTANCE.increment();
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
        
        public IncrementerThread3() {
            this.c=SingletonClassic.getInstance();
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
