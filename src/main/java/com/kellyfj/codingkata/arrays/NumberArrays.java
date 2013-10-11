package com.kellyfj.codingkata.arrays;

import java.util.Arrays;

public class NumberArrays {

    /**
     * http://codercareer.blogspot.com/2011/10/no-09-numbers-with-given-sum.html
     * Given an array, please determine whether it contains three numbers whose sum equals to 0.
     */    
    public static boolean doesSumOfTwoEqualN(int[] array, int target) {
       
        if(array.length < 2)
            throw new IllegalArgumentException("Array length should be 2 or greater");
        
        Arrays.sort(array);
        
        int startPointer=0;
        int endPointer = array.length-1;
        
        while(startPointer < endPointer) {
            int sum = array[startPointer] + array[endPointer];
            if(sum == target) {
                return true;
            } else {
                if (sum > target) {
                    endPointer--;
                } else {
                    startPointer++;
                }
            }
        }
        return false;
    }  
        
        public static boolean doesSumOfThreeEqualN(int[] array, int target) {
            if(array.length < 3)
                throw new IllegalArgumentException("At least 3 elements expected in the array");
            Arrays.sort(array);
            
            int pointer = 0;
            while(pointer < array.length-1) {
                int sumOfTwoTarget = target - array[pointer];
                boolean res = doesSumOfTwoEqualN(array, sumOfTwoTarget);
                if(res){
                    return true;
                }
                pointer++;
            }
            return false;
        }
        
    }
