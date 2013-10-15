package com.kellyfj.codingkata.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
                System.out.println("Numbers ["+array[startPointer] +"] and ["+array[endPointer]+"] sum to ["+target+"]");
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
        
        public static void shuffle(Object[] a) {
            Random rnd = new Random();
            int mid = a.length / 2;
            for (int i = mid; i < a.length; i++) {
                int lo = rnd.nextInt(mid);
                Object buffer = a[lo];
                a[lo] = a[i];
                a[i] = buffer;
            }
        }
        
        public static void printArray(Object[] a) {
            for(int i=0; i< a.length; i++) {
                System.out.print(a[i].toString()+ " ");
            }
            System.out.println();
        }
        
        public static void printArray(int[] a) {
            for(int i=0; i< a.length; i++) {
                System.out.print(a[i]+ " ");
            }
            System.out.println();
        }
        
        public static int lengthOfLongestMonotonicSeries(int[] input) {

            int max=1;
            int num=1;
            for(int i=0; i< input.length-1; i++) {
 
                if(input[i] == input[i+1]-1) {
                    num++;
                    max=num>max ? num: max; 
                } else {
                    num = 0;
                }
            }
            
            return max;
        }
        
        
        public static void dutchNationalFlagBasic(int[] input)
        {
            int numZeroes=0;
            int numOnes=0;
            int numTwos=0;
            
            for(int i=0; i< input.length; i++) {
                if(input[i]==0)
                    numZeroes++;
                else if(input[i] ==1)
                       numOnes++;
                else if(input[i]==2)
                    numTwos++;
            }
            
            for(int i=0; i< numZeroes; i++)
                input[i]=0;
            for(int i=numZeroes; i< numZeroes+numOnes; i++)
                input[i]=1;    
            for(int i=numZeroes+numOnes; i< numZeroes+numOnes+numTwos; i++)
                    input[i]=2;
            
        }
        
        public static void dutchNationalFlagOnePass(int[] a, int mid) {
            int startIndex = 0;
            int endIndex = a.length - 1;
            int temp;

            for (int i = 0; i <= endIndex ;) {
                if (a[i] < mid) {
                    temp = a[i];
                    a[i] = a[startIndex];
                    a[startIndex] = temp;
                    i++;
                    startIndex++;
                } else if (a[i] > mid) {
                    temp = a[i];
                    a[i] = a[endIndex];
                    a[endIndex] = temp;
                    endIndex--;
                    // do not increment i. We have to revisit this again
                } else {
                    i++;
                }
            }
        }
        
        
        public static int countBinaryOnes(int number) {
            String binaryString = Integer.toBinaryString(number);
            return binaryString.length() - binaryString.replace("1", "").length();
        }
        
    public static String getBinaryRepresentation(int number) {
        int b[] = new int[32];
        int i = 0;
        while (number != 0) {
            i++;
            b[i] = number % 2;
            number = number / 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j > 0; j--) {
            sb.append(b[j]);
        }
        return sb.toString();
    }
        
    public static List<Set<Integer>> powerSet(int[] array) {
        List<Set<Integer>> returnList = new ArrayList<Set<Integer>>();
        if (array.length == 0) {
            returnList.add(new HashSet<Integer>());
            return returnList;
        }

        int head = array[0];
        int[] rest = new int[] {};
        if (array.length >= 2)
            rest = Arrays.copyOfRange(array, 1, array.length);

        for (Set<Integer> powerSets : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(powerSets);
            returnList.add(newSet);
            returnList.add(powerSets);
        }
        return returnList;
    }
    
    public static double calcSquareRoot(double num) {
        double guess = 1;
        double diff = Math.abs(guess * guess - num);
        while (diff > 0.0001) {
            diff = Math.abs(guess * guess - num);
            guess = (guess + num / guess) / 2;
            //System.out.println("New Guess is [" + guess + "]");
        }
        return guess;
    }
}
