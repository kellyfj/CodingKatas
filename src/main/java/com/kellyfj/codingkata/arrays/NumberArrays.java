package com.kellyfj.codingkata.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class NumberArrays {

    /**
     * GOAL: Get the min and max using 3N/2 comparisons (as opposed to 2N)
     * 
     * Initialize min and max as minimum and maximum of the first two
     * elements respectively. For rest of the elements, pick them in pairs and
     * compare their maximum and minimum with max and min respectively.
     * http://www.geeksforgeeks.org/maximum-and-minimum-in-an-array/
     * 
     * @param arr
     * @return
     */
    public static MinMax getMinMax(int[] arr) {
        MinMax minmax = new MinMax();

        minmax.comparisons=1;
        if (arr[0] > arr[1]) {
            minmax.max = arr[0];
            minmax.min = arr[1];
        } else {
            minmax.min = arr[0];
            minmax.max = arr[1];
        }
        int i = 1;
    
        // In the while loop, pick elements in pair and compare the pair with current min/max
        while (i < arr.length - 1) {
            minmax.comparisons++;
            if (arr[i] > arr[i + 1]) {
                minmax.comparisons++;
                if (arr[i] > minmax.max) minmax.max = arr[i];
                minmax.comparisons++;
                if (arr[i + 1] < minmax.min) minmax.min = arr[i + 1];
            } else {
                minmax.comparisons++;
                if (arr[i + 1] > minmax.max)  minmax.max = arr[i + 1];
                minmax.comparisons++;
                if (arr[i] < minmax.min)  minmax.min = arr[i];
            }
            
            i += 2; //Increment the index by 2 as two elements are processed
            //If we are near the end - ensure we compare the last two numbers
            if(i == arr.length - 1) i -= 1;
        }

        return minmax;
    }

    public static class MinMax {

        public int comparisons;
        public int min;
        public int max;

    }

    /**
     * http://codercareer.blogspot.com/2011/10/no-09-numbers-with-given-sum.html
     * Given an array, please determine whether it contains three numbers whose
     * sum equals to a target.
     * TIME COMPLEXITY: O(nlogn) due to sort
     */
    public static boolean doesSumOfTwoEqualN_orderNlogN(int[] array, int target) {
        if (array.length < 2)
            throw new IllegalArgumentException("Array length should be 2 or greater");
        //This is O(n log n)
        Arrays.sort(array);

        int startPointer = 0;
        int endPointer = array.length - 1;
        //This is O(n)
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

    /**
     * Different variant of Sum-of-Two-equals-N
     * 
     * TIME COMPLEXITY: O(N) due to two sequential loops
     */
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

    /**
     * Sum-of-three Algorithm
     * TIME COMPLEXITY: O(n)*O(n log n) = O(n^2)
     */
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

    /**
     * http://stackoverflow.com/questions/2070359/finding-three-elements-in-an-array-whose-sum-is-closest-to-an-given-number
     *
     * TIME COMPLEXITY: Still O(n^2) but less comparisons
     */
    public static boolean doesSumOfThreeEqualN_faster(int[] A, int target) {
        if (A.length < 3)
            throw new IllegalArgumentException("At least 3 elements expected in the array");
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i++) {
            int j = i+1; // Start where i is.
            int k = A.length-1; // Start at the end of the array.

            while (k >= j) {
                int sum = A[i] + A[j] + A[k];
                if ( sum == target ) return true;

                // We didn't match. Let's try to get a little closer:
                if (sum > target)
                    k--;
                else
                    j++;
            }
            // When the while-loop finishes, j and k have passed each other and
            // there's no more useful combinations that we can try with this i.
        }
        return false;
    }
    
    /**
     * Randomly shuffle object in an array
     * @param a
     */
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

    /**
     * TIME COMPLEXITY: O(n)
     */
    public static int lengthOfLongestMonotonicSeries(int[] input) {
        int maxLength = 1;
        int currLength = 1;
        for (int i = 0; i < input.length - 1; i++) {

            if (input[i] == input[i + 1] - 1) {
                currLength++;
                maxLength = currLength > maxLength ? currLength : maxLength;
            } else {
                currLength = 0;
            }
        }

        return maxLength;
    }

    /**
     * EPIJava 5.1
     * 
     * http://en.wikipedia.org/wiki/Dutch_national_flag_problem
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n) for the new array
     */
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

    /**
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1) since we are swapping in place
     */
    public static void dutchNationalFlagOnePass_Order1AdditionalSpace(int[] a, int mid) {
        int startIndex = 0;
        int endIndex = a.length - 1;

        for (int i = 0; i <= endIndex;) {
            if (a[i] < mid) {
                swap(a, startIndex, i);
                i++;
                startIndex++;
            } else if (a[i] > mid) {
                swap(a, endIndex, i);
                endIndex--;
                // do not increment i. We have to revisit this again
                //in case a[endIndex] < mid
            } else {
                i++;
            }
        }  
    }
    
    /**
     * Counting sort for integers in the range 0 to max_range 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n) + O(k) = O(n)
     */
    public static int[] countingSort(int[] a, int max_range) {
    	int[] valueCounts = new int[max_range+1]; //0 to max_range means max_range+1 values
    	
    	//O(n) counting
    	for(int i=0; i< a.length; i++) {
    		int value = a[i];
    		valueCounts[value] += 1;
    	}
    	
    	int[] answer = new int[a.length];
    	int k=0;
    	//O(nk) "sorting"
    	for(int i=0; i< valueCounts.length; i++) {
    		for(int j=0; j< valueCounts[i]; j++) {
    			answer[k++] = i;
    		}
    	}
    	
    	return answer;
    }

    /**
     * Binary search in array that is sorted
     * 
     * TIME COMPLEXITY: O(log n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int findFirstOccurrenceOfItemInSortedArray(int[] a, int lookFor) {
        int leftPointer=0;
        int rightPointer=a.length-1;
        int result=-1;
        
        while(leftPointer <= rightPointer) {
            int pivot = leftPointer+((rightPointer-leftPointer) / 2);
            int pivotValue = a[pivot];
            if(pivotValue > lookFor) {
                rightPointer = pivot-1; //Reduce search space 
            } else if (pivotValue == lookFor) {
                result=pivot; //Record solution and keep searching to the left
                rightPointer = pivot -1;
            } else {  //pivot < lookfor
                leftPointer = pivot+1;
            }
        }
        
        return result;
    }
    
    private static void swap(int[] a, int startIndex, int i) {
        int temp;
        temp = a[i];
        a[i] = a[startIndex];
        a[startIndex] = temp;
    }

    public static int countBinaryOnes(int number) {
        String binaryString = Integer.toBinaryString(number);
        return binaryString.length() - binaryString.replace("1", "").length();
    }

    public static String getBinaryRepresentation(int number) {
        int bits[] = new int[32];
        int numbits = 0;
        while (number != 0) {
        	numbits++;
        	bits[numbits] = number % 2;
            number = number / 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = numbits; j > 0; j--) {
            sb.append(bits[j]);
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

        for (Set<Integer> aSet : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(aSet);
            returnList.add(newSet);
            returnList.add(aSet);
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
            if (newSet.size() == k)
                returnList.add(newSet);
            if (s.size() == k)
                returnList.add(s);
        }
        return returnList;
    }


    public static void printPowerSet(List<Set<Integer>> listOfSets) {
        System.out.println("POWERSET");
        for (Set<Integer> set : listOfSets) {
            System.out.print("{");
            for (Integer i : set) {
                System.out.print(i + " ");
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

    public static List<List<Integer>> findCombosToReachTargetSum(List<Integer> numbers, int target) {
        List<List<Integer>> ret = findCombosToReachTargetSum(numbers, target, new ArrayList<Integer>());
        return ret;
    }

    private static List<List<Integer>> findCombosToReachTargetSum(List<Integer> numbers, 
            int target, List<Integer> intermed) {
        int intermedSum = 0;
        for (int i : intermed)
            intermedSum += i;

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (intermedSum == target) {
            ret = new ArrayList<List<Integer>>();
            ret.add(intermed);
        }

        if (intermedSum > target)
            return null;

        // Pick a number
        for (int i = 0; i < numbers.size(); i++) {
            int n = numbers.get(i);

            // Start adding the other numbers until we reach conditions above
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            for (int j = i + 1; j < numbers.size(); j++)
                remaining.add(numbers.get(j));

            ArrayList<Integer> intermedCopy = new ArrayList<Integer>(intermed);
            intermedCopy.add(n);
            
            List<List<Integer>> temp = findCombosToReachTargetSum(remaining, 
                    target, intermedCopy);
            if (temp != null) {
                ret.addAll(temp);
            }
        }

        return ret;
    }
    
    public static int[] merge(int[] a, int[] b) {
        int[] answer = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length)
        {
            if (a[i] < b[j])       
                answer[k++] = a[i++];
            else        
                answer[k++] = b[j++];               
        }

        //a[] longer than b[]
        while (i < a.length)  
            answer[k++] = a[i++];

        //b[] longer than a[]
        while (j < b.length)    
            answer[k++] = b[j++];

        return answer;
    }
    
    public static Integer[] mergeRemovingDupes(int[] a, int[] b) {
        Set<Integer> s = new HashSet<Integer>();
        
        for(int i: a) {
            if(!s.contains(i))
                s.add(i);
        }
        for (int i : b) {
            if(!s.contains(i))
                s.add(i);
        }

        return s.toArray(new Integer[0]);
    }
    
	public static int getKthSmallestElementInTwoSortedArrays(int[] array1,
			int[] array2, int k) {
		if(k > array1.length + array2.length) {
			throw new IllegalArgumentException("k is too large");
		}
		int index1 = 0, index2 = 0;
		
		//Advance pointers until 'k' exhausted or we reach end of an array
		while (k != 1 && index1 < array1.length && index2 < array2.length) {
			if (array1[index1] < array2[index2])
				index1++;
			else
				index2++;
			k--;
		}

		//If you haven't reached end of either array - pick smallest of remaining pointers
		if (index1 < array1.length && index2 < array2.length) {
			//Assert.assertTrue(k==1);
			if (array1[index1] > array2[index2])
				return array2[index2];
			else
				return array1[index1];
		} else { //Else reached end of one array
			if (index1 >= array1.length)
				return array2[index2 + (k-1)];
			else
				return array1[index1 + (k-1)];

		}
	}
	
	/**
	 * TIME COMPLEXITY: O(n)
	 * SPACE COMPLEXITY: O(n)
	 */
	public static boolean containsDuplicates_orderNspace(int[] array) {
		Set<Integer> s = new HashSet<Integer>(array.length);
		for(int i: array) {
			if(s.contains(i))
				return true;
			else
				s.add(i);
		}
		return false;
	}
	
	/**
	 * Only works for positive numbers when numbers are in the range 0 to n-1
	 * TIME COMPLEXITY: O(n)
	 * SPACE COMPLEXITY: O(1)
	 */
	public static boolean containsDuplicates_order1space(int[] array) {
		for(int i=0; i< array.length; i++) {
			int tmpIndex = Math.abs(array[i]);
			if(array[tmpIndex] < 0) {
				return true;
			} else { //tmpIndex > 0
				array[tmpIndex] = -1 * array[tmpIndex];
			}
			
		}
		return false;
	}
	
	/**
	 * TIME COMPLEXITY: O(2n) = O(n)
	 * SPACE COMPLEXITY: O(n)
	 */
	public static int findMostFrequentlyOccurringElement(int[] array) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(array.length);
		for(int i: array) {
			if(map.containsKey(i)) {
				Integer a = map.get(i);
				map.put(i, a+1);
			} else {
				map.put(i, 1);
			}
		}
		
		int maxCount = 0;
		int mostFrequentlyOccurElement = array[0];
		for(int i: array) {
			Integer a = map.get(i);
			if(a > maxCount) {
				maxCount = a;
				mostFrequentlyOccurElement = i;
			}
		}
		
		return mostFrequentlyOccurElement;
	}
	
	/**
	 * We are given a list of n-1 integers in the range 1 to n with no duplicates.
	 * Find the missing integers.
	 * 
	 * TIME COMPLEXITY: O(n)
	 * SPACE COMPLEXITY: O(1)
	 */
	public static int findMissingNumber(int[] array) {
		int n = array.length+1;
		int targetsum = n*(n+1)/2;
		for(int i=0; i<array.length; i++) {
			targetsum -= array[i];
		}
		
		return targetsum;
	}
	
	
	/**
	 * Uses O(n) space
	 */
	public static List<Integer> incrementArbitraryPrecisionInteger(List<Integer> intList) {
	    if(intList.isEmpty()) {
	        return Collections.emptyList();
	    }
	    int listSize = intList.size();
	    //Important notice how we assign objects not just setting the capacity
	    //FYI it is fixed size 
	    List<Integer> returnList = Arrays.asList(new Integer[listSize+1]);
	    int carry=0;
	    
	    returnList.set(0, 0); //This number is in case of a carry later
	    
        int currInt = intList.get(listSize - 1);
        if (currInt == 9) {
            returnList.set(listSize, 0);
            carry = 1;
        } else { // carry remains zero
            returnList.set(listSize, currInt + 1);
        }
	    
        for (int i = listSize - 2; i >= 0; i--) {
            currInt = intList.get(i);
            if (carry > 0) {
                if (currInt == 9) {
                    returnList.set(i+1, 0);
                    carry = 1;
                } else {
                    returnList.set(i+1, currInt + carry);
                    carry = 0;
                }
            } //else continue
        }
        
        if(carry == 1) {
            returnList.set(0, 1);
        }
        
        return returnList;
	    
	}
	
    public static List<Integer> plusOneOrder1Space(List<Integer> A) {
        if (A.isEmpty()) {
            return Collections.emptyList();
        }

        int n = A.size() - 1;
        A.set(n, A.get(n) + 1);

        for (int i = n; i > 0 && A.get(i) == 10; --i) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }

        if (A.get(0) == 10) {
            A.set(0, 1);
            A.add(0);

        }
        return A;
    }
    
    /**
     * EPIJ 5.3: Remove duplicates from a Sorted Array
     */
    public static int removeDuplicatesFromSortedArray(List<Integer> A) {
        if(A.isEmpty()) {
            return 0;
        }
        
        int writeIndex = 1;
        for(int i=1; i< A.size(); i++) {
            
            if(!A.get(writeIndex-1).equals(A.get(i))) {
                A.set(writeIndex++, A.get(i));
            } //else increment i without increment writeIndex
        }
        //OPTIONAL: remove from writeIndex onwards
        int origSize = A.size();
        for(int i=writeIndex; i < origSize; i++) {
            A.remove(A.size()-1); //remove from the end backwards
        }
        
        return writeIndex;
    }
}
