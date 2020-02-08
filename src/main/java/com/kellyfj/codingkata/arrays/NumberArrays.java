package com.kellyfj.codingkata.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
        //Use a map in-case the number and it's complement are the same 
        // i.e. n = n/2 + n/2 so we need > 1 copy
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
     * EPIJ 5.1: The Dutch National Flag Problem
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
     * EPIJ 11.1: Search a sorted array for first occurrence of an integer
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
    
	/**
	 * EPIJ 11.3: Search a cyclically sorted array. 
	 * Cyclically sorted means it is possible to cyclically shift the entries so that it becomes sorted. 
	 * Example: 378 - 478 - 550 - 631 - 103 - 203 - 220 - 234 - 279 - 368 
	 * Brute force approach is to iterate through the array comparing against running minimum - complexity
	 * O(n). But we should take advantage of properties of the array if A[m] >
	 * A[n-1] then the min value must be in the range [m+1, n-1]
	 * Time Complexity: O(log n)
	 */
	public static int searchSmallest(List<Integer> A) {
		int left = 0;
		int right = A.size() - 1;

		while (left < right) {
			int mid = left + ((right - left) / 2);
			if (A.get(mid) > A.get(right)) {
				// Min must be in A.sublist(mid+1, right+1)
				left = mid + 1;
			} else {
				// A.get(mid) < A.get(right)
				// Minimum cannot be in A.sublist(mid+1, right+1) so it must be in
				// A.sublist(left, mid+1)
				right = mid;
			}
		}
		//Loop ends when left == right;
		return A.get(left);
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
    
    /**
     * EPIJ 11.4: Calculate the Integer Square Root
     * Time Complexity: O(log k)
     */
	public static int calcSquareRoot(int k) {
		long left = 0, right = k;
		// Candidate interval [left, right] where everything before left has
		// square <= k, and everything after right has square > k
		while (left <= right) {
			long mid = left + ((right - left) / 2);
			long midSquared = mid * mid;
			if (midSquared <= k) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return (int) left - 1;
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
    	if(a.length == 0) 
    		return b;
    	if(b.length == 0)
    		return a;
    	
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
    
    /**
     * TIME COMPLEXITY: O(m+n)
     * SPACE COMPLEXITY: O(1)
     */
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
		//Either k=1 or one array exhausted

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
		//@TODO: Need to really understand how this works
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
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(array.length);
		for (int i : array) {
			if (map.containsKey(i)) {
				Integer a = map.get(i);
				map.put(i, a + 1);
			} else {
				map.put(i, 1);
			}
		}

		int maxCount = 0;
		int mostFrequentlyOccurElement = array[0];
		for (Integer i : map.keySet()) {
			Integer a = map.get(i);
			if (a > maxCount) {
				maxCount = a;
				mostFrequentlyOccurElement = i;
			}
		}

		return mostFrequentlyOccurElement;
	}
	
	/**
	 * EPIJ 11.10: We are given a list of n-1 integers in the range 1 to n with no duplicates.
	 * Find the missing integers.
	 * 
	 * TIME COMPLEXITY: O(n)
	 * SPACE COMPLEXITY: O(1)
	 */
	public static int findMissingNumber(int[] array) {
		int n = array.length + 1;
		int targetsum = (n * (n + 1)) / 2;
		for (int i = 0; i < array.length; i++) {
			targetsum -= array[i];
		}

		return targetsum;
	}

	public static int findDuplicateNumber(int[] array) {
		int n = array.length - 1;
		int targetSum = (n * (n + 1)) / 2;
		int actualSum = 0;
		for (int i = 0; i < array.length; i++) {
			actualSum += array[i];
		}

		return actualSum - targetSum;
	}
	
	/**
	 * EPIJ: 5.2 Increment an Arbitrary Precision Integer
	 * Brute force version
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
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
	
	/**
	 * EPIJ: 5.2 Increment an Arbitrary Precision Integer
	 * More efficient version.
	 * 
	 * Time Complexity: TBD
	 * Space Complexity: O(1)
	 */
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
     * EPIJ 5.5: Remove duplicates from a Sorted Array
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - return the size of the array with duplicates moved to end
     */
    public static int removeDuplicatesFromSortedArray(List<Integer> A) {
        if(A.isEmpty()) {
            return 0;
        }
        if(A.size() == 1) {
        	return 1;
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
    
    /**
     * EPIJ: 5.6 - Buy and Sell a Stock once
     * 
     * Time Complexity: O(n^2)
     */
	public static Tuple buyAndSellOnceBruteForce(List<Double> prices) {
		if (prices.isEmpty() || prices.size() < 2) {
			throw new IllegalArgumentException("Need at least two prices");
		}

		Tuple bestBuyAndSellTuple = new Tuple(prices.get(0), prices.get(1));
		for (int buyAtPointer = 1; buyAtPointer < prices.size() - 1; buyAtPointer++) {
			for (int sellAtPointer = buyAtPointer; sellAtPointer < prices.size() - 1; sellAtPointer++) {
				Tuple t = new Tuple(prices.get(buyAtPointer), prices.get(sellAtPointer));
				if (t.getProfit() > bestBuyAndSellTuple.getProfit()) {
					bestBuyAndSellTuple = t;
				}
			}
		}
		return bestBuyAndSellTuple.getProfit() < 0 ? null : bestBuyAndSellTuple;
	}
    
	/**
	 * Key Insight
	 * Maximum profit can be made by selling on the day determined by the minimum of the stock prices over
	 * the previous days so we 
	 * 	1) Iterate through the list
	 *  2) Keep track of the minimum element
	 *  3) If the difference between the current element and the min element is greater than max profit
	 *      then update the maxProfit
	 * Time Complexity: O(n)
	 */
	public static Tuple buyAndSellOnceOrderN(List<Double> prices) {
		if (prices.isEmpty() || prices.size() < 2) {
			throw new IllegalArgumentException("Need at least two prices");
		}

		Double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
		Tuple bestTuple = null;
		for (Double price : prices) {
			Double maxProfitBefore = maxProfit;
			maxProfit = Math.max(maxProfit, price - minPrice);
			if (maxProfit > maxProfitBefore) {
				bestTuple = new Tuple(minPrice, price);
			}
			minPrice = Math.min(minPrice, price);
		}		
		System.out.println("Max Profit "+ maxProfit);
		return bestTuple;
	}
	
    public static class Tuple {
    	public Double buyAt;
    	public Double sellAt;
    	
    	public Tuple(Double buyAt, Double sellAt) {
    		this.buyAt = buyAt;
    		this.sellAt = sellAt;
    	}
    	
    	public Double getProfit() {
    		return (sellAt - buyAt);
    	}
    	
    	@Override
    	public String toString() {
    		return new String("buyAt "+ buyAt + " and sellAt " + sellAt );
    	}
    }
    
    /**
     * EPIJ 5.9 Enumerate all primes to n
     * 
     * Start with 0,1 as NOT prime, 2 is a prime then sieve out all multiples of 2
     * from a boolean list, 3 is a prime etc. continue . . . 
     * 
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     */
    public static List<Integer> generatePrimes(int n) {
    	List<Integer> primes = new ArrayList<>();
    	
    	//Initially set prime status to true
    	List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n+1, true));
    	
    	isPrime.set(0, false);
    	isPrime.set(1, false);
    	for(int p = 2; p <= n; ++p) {
    		if(isPrime.get(p)) {
    			primes.add(p);
    			//Sieve p's multiples from isPrime 
    			for(int i=p; i <=n; i+=p) {
    				isPrime.set(i, false);
    			}
    		}
     		
    	}
 
    	return primes;
    }
    
    /**
     * EPIJ 5.12: Sample Offline Data
     * 
     * The key to efficiently building a random subset of size k is to first build one of size k-1
     * and then add one more element, selected randomly from the set
     * For k > 1 we begin by choosing one element at random and place that at A[0] and repeat
     * the same process again with the n-1 element subarray A[1, n-1]
     * Eventually the random subset occupies that slots A[0, k-1] and the remaining elements are in the 
     * last n-k nodes
     */   
    public static void randomSampling(int k, List<Integer> A) {
    	Random gen = new Random();
    	for(int i=0; i< k; i++) {
    		Collections.swap(A, i, i + gen.nextInt(A.size()-1));
    	}
    }
    
    /**
     * EPIJ 5.13 Sample Online Data
     * 
     * Design a program that takes as input a size (k) and reads packets, continuously maintaining
     * a uniform random subset of size (k) of the read packets.
     * 
     * Suppose we read the first n packets and have a random subset of k of them. 
     * When we read the (n+1)the packet it should belong to the new subset with the probability k/(n+1).
     * If we choose one of the packets in the subset randomly to remove the resulting collection
     * will be a random subset of the n+1 packets. 
     */
	public static List<Integer> onlineRandomSample(Iterator<Integer> sequence, int k) {
		List<Integer> runningSample = new ArrayList<>(k);

		// Store the first 'k' elements
		for (int i = 0; i < k && sequence.hasNext(); i++) {
			runningSample.add(sequence.next());
		}

		int numSeenSoFar = k;
		Random gen = new Random();
		while (sequence.hasNext()) {
			Integer x = sequence.next();
			numSeenSoFar++;
			// Generate a random number in [0, numSeenSoFar] and if this number is in
			// [0, k-1] we replace that element from the sample with x
			int indexToReplace = gen.nextInt(numSeenSoFar);
			if (indexToReplace < k) {
				runningSample.set(indexToReplace, x);
			}
		}
		return runningSample;
	}
    
}
