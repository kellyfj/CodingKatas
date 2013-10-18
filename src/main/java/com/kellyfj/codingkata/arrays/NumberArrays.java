package com.kellyfj.codingkata.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class NumberArrays {

    /**
     * http://codercareer.blogspot.com/2011/10/no-09-numbers-with-given-sum.html
     * Given an array, please determine whether it contains three numbers whose
     * sum equals to 0.
     */
    public static boolean doesSumOfTwoEqualN_orderNlogN(int[] array, int target) {
        if (array.length < 2)
            throw new IllegalArgumentException("Array length should be 2 or greater");
        Arrays.sort(array);
        
        int startPointer = 0;
        int endPointer = array.length - 1;
        while (startPointer < endPointer) {
            int sum = array[startPointer] + array[endPointer];
            if (sum == target) {
                System.out.println("Numbers [" + array[startPointer] + "] and [" + array[endPointer] + "] sum to ["
                        + target + "]");
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

    public static boolean doesSumOfTwoEqualN_orderN(int[] array, int sumOfTwoTarget) {
        if (array.length < 2)
            throw new IllegalArgumentException("Array length should be 2 or greater");
        Map<Integer, Integer> numberCount = new HashMap<Integer, Integer>();

        // Pre-process
        for (int i = 0; i < array.length; i++) {
            int number = array[i];
            if (numberCount.containsKey(number)) {
                Integer c = numberCount.get(number);
                c++;
                numberCount.put(number, c);
            } else {
                numberCount.put(number, 1);
            }
        }

        // Check against Map
        for (int i = 0; i < array.length; i++) {
            int firstNum = array[i];
            int complement = sumOfTwoTarget - array[i];
            if (numberCount.containsKey(complement)) {
                if (firstNum == complement) {
                    int count = numberCount.get(complement);
                    if (count >= 2)
                        return true;
                } else {
                    return true;
                }

            }
        }

        return false;
    }
    
    public static boolean doesSumOfThreeEqualN(int[] array, int target) {
        if (array.length < 3)
            throw new IllegalArgumentException("At least 3 elements expected in the array");
        Arrays.sort(array);
        int pointer = 0;
        while (pointer < array.length - 1) {
            int sumOfTwoTarget = target - array[pointer];
            boolean res = doesSumOfTwoEqualN_orderN(array, sumOfTwoTarget);
            if (res) {
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
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i].toString() + " ");
        }
        System.out.println();
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static int lengthOfLongestMonotonicSeries(int[] input) {

        int max = 1;
        int num = 1;
        for (int i = 0; i < input.length - 1; i++) {

            if (input[i] == input[i + 1] - 1) {
                num++;
                max = num > max ? num : max;
            } else {
                num = 0;
            }
        }

        return max;
    }

    public static void dutchNationalFlagBasic(int[] input) {
        int numZeroes = 0;
        int numOnes = 0;
        int numTwos = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0)
                numZeroes++;
            else if (input[i] == 1)
                numOnes++;
            else if (input[i] == 2)
                numTwos++;
        }

        for (int i = 0; i < numZeroes; i++)
            input[i] = 0;
        for (int i = numZeroes; i < numZeroes + numOnes; i++)
            input[i] = 1;
        for (int i = numZeroes + numOnes; i < numZeroes + numOnes + numTwos; i++)
            input[i] = 2;

    }

    public static void dutchNationalFlagOnePass(int[] a, int mid) {
        int startIndex = 0;
        int endIndex = a.length - 1;
        int temp;

        for (int i = 0; i <= endIndex;) {
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
 
    public static List<Set<Integer>> setsOfSizeK(int[] array, int k) {
        List<Set<Integer>> returnList = new ArrayList<Set<Integer>>();
        if (array.length == 0) {
            returnList.add(new HashSet<Integer>());
            return returnList;
        }

        int head = array[0];
        int[] rest = new int[] {};
        if (array.length >= 2)
            rest = Arrays.copyOfRange(array, 1, array.length);

        for (Set<Integer> s : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(s);
            if(newSet.size()==k) returnList.add(newSet);
            if(s.size() ==k) returnList.add(s);
        }
        return returnList;
    }
    
    public static void printPowerSet(List<Set<Integer>> listOfSets) {
        System.out.println("POWERSET");
        for(Set<Integer> set : listOfSets) {
            System.out.print("{");
            for(Integer i : set) {
                System.out.print(i+ " ");
            }
            System.out.println("}");
        }
    }

    public static double calcSquareRoot(double num) {
        double guess = 1;
        double diff = Math.abs(guess * guess - num);
        while (diff > 0.0001) {
            diff = Math.abs(guess * guess - num);
            guess = (guess + num / guess) / 2;
            // System.out.println("New Guess is [" + guess + "]");
        }
        return guess;
    }
    
    
    private static void findCombinationsToReachTargetSum(List<Integer> numbers, int target, List<Integer> intermed) {
        int intermedSum = 0;
        for (int i : intermed)
            intermedSum += i;
        
        if (intermedSum == target)
            System.out.println("sum(" + Arrays.toString(intermed.toArray()) + ")=" + target);
        
        if (intermedSum >= target)
            return;
        
        //Pick a number
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            int n = numbers.get(i);
            
            //Start adding the other numbers until we reach conditions above
            for (int j = i + 1; j < numbers.size(); j++)
                remaining.add(numbers.get(j));
            ArrayList<Integer> intermedCopy = new ArrayList<Integer>(intermed);
            intermedCopy.add(n);
            findCombinationsToReachTargetSum(remaining, target, intermedCopy);
        }
    }

    public static void findCombinationsToReachTargetSum(List<Integer> numbers, int target) {
        findCombinationsToReachTargetSum(numbers, target, new ArrayList<Integer>());
    }
}
