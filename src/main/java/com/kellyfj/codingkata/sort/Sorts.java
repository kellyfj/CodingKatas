package com.kellyfj.codingkata.sort;

public class Sorts {

    
    public static void bubbleSort(int[] input)
    {
        long start = System.currentTimeMillis();
        //int[] returnArray = new int[input.length];
       int numSwaps=Integer.MAX_VALUE;
       int numPasses=0;
        while (numSwaps >0) {
            numSwaps=0;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] > input[i + 1]) {
                    int temp = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = temp;
                    numSwaps++;
                }
            }
            numPasses++;
        }
        
        long end = System.currentTimeMillis();
        System.out.println("Sorting took "+(end-start)+" ms in "+numPasses+" passes");
    }
    
    public static void selectionSort(int[] input)
    {
    }
}
