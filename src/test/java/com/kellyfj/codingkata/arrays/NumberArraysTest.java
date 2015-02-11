package com.kellyfj.codingkata.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.kellyfj.codingkata.arrays.NumberArrays.MinMax;

public class NumberArraysTest extends TestCase {

    public void testDoesSumOfTwoEqualN_Error() {
        int[] testArray = { 1 };
        try {
            NumberArrays.doesSumOfTwoEqualN_orderNlogN(testArray, 10);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
        }       
    }
    
    public void testDoesSumOfTwoEqualN_False() {  
        int[] testArray = {1,2,3,5,6};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderNlogN(testArray, 10);
        assertFalse(res);    
    }
    
    public void testDoesSumOfTwoEqualNTrue() {
        int[] testArray = {1,2,3,5,5};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderNlogN(testArray, 10);
        assertTrue(res);
    }

    
    public void testDoesSumOfTwoEqualNTrue_unsorted() {
        int[] testArray = {5,4,3,2,1,9};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderNlogN(testArray, 10);
        assertTrue(res);
    }
    
    public void testDoesSumOfTwoEqualNTrue_edge() {
        int[] testArray = {1, 5, 8};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderNlogN(testArray, 10);
        assertFalse(res);
    }
    
    /**
     * Test Order N solution
     */
    public void testDoesSumOfTwoEqualN_Error2() {
        int[] testArray = { 1 };
        try {
            NumberArrays.doesSumOfTwoEqualN_orderN(testArray, 10);
            fail("Exception expected");
        } catch (IllegalArgumentException expected) {
        }       
    }
    
    public void testDoesSumOfTwoEqualN_False2() {  
        int[] testArray = {1,2,3,5,6};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderN(testArray, 10);
        assertFalse(res);    
    }
    
    public void testDoesSumOfTwoEqualNTrue2() {
        int[] testArray = {1,2,3,5,5};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderN(testArray, 10);
        assertTrue(res);
    }

    
    public void testDoesSumOfTwoEqualNTrue_unsorted2() {
        int[] testArray = {5,4,3,2,1,9};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderN(testArray, 10);
        assertTrue(res);
    }
    
    public void testDoesSumOfTwoEqualNTrue_edge2() {
        int[] testArray = {1, 5, 8};
        boolean res = NumberArrays.doesSumOfTwoEqualN_orderN(testArray, 10);
        assertFalse(res);
    }
    public void test_doesSumOfThreeEqualN() {
        int[] testArray = {5,4,3,2,1,9};
        boolean res = NumberArrays.doesSumOfThreeEqualN(testArray, 10);
        assertTrue(res);
    }
    
    public void test_doesSumOfThreeEqualN_false() {
        int[] testArray = {1,2,3};
        boolean res = NumberArrays.doesSumOfThreeEqualN(testArray, 10);
        assertFalse(res);
    }
    
    public void test_doesSumOfThreeEqualN_edge() {
        int[] testArray = {2,3,5};
        boolean res = NumberArrays.doesSumOfThreeEqualN(testArray, 4);
        assertFalse(res);
    }
  
    public void test_doesSumOfThreeEqualNFaster() {
        int[] testArray = {5,4,3,2,1,9};
        boolean res = NumberArrays.doesSumOfThreeEqualN_faster(testArray, 10);
        assertTrue(res);
    }
    
    public void test_doesSumOfThreeEqualN_falseFaster() {
        int[] testArray = {1,2,3};
        boolean res = NumberArrays.doesSumOfThreeEqualN_faster(testArray, 10);
        assertFalse(res);
    }
    
    public void test_doesSumOfThreeEqualN_edgeFaster() {
        int[] testArray = {2,3,5};
        boolean res = NumberArrays.doesSumOfThreeEqualN_faster(testArray, 4);
        assertFalse(res);
    }
   
    public void test_doesSumOfThreeEqualN_perf() {
        int MAX = 5000;
        int[] testArray = new int[MAX];
        for(int i=0; i<MAX; i++) {
            testArray[i] = i;
        }
        
        long s1 = System.currentTimeMillis();
        boolean res = NumberArrays.doesSumOfThreeEqualN(testArray, MAX*MAX);
        long e1 = System.currentTimeMillis();   
        boolean res2 = NumberArrays.doesSumOfThreeEqualN_faster(testArray, MAX*MAX);
        long e2 = System.currentTimeMillis();
        
        
        assertFalse(res);
        assertEquals(res,res2);
        long delta1 = e1-s1;
        long delta2 = e2-e1;
        System.out.println("First Method took "+(e1-s1)+"ms vs "+(e2-e1)+" ms");
        
        assertTrue(delta1 > delta2);
    }
    
    public void test_doesSumOfThreeEqualN_error() {
        int[] testArray = {1,2};
        try{ 
            NumberArrays.doesSumOfThreeEqualN(testArray, 10);
            fail("Exception expected");
        }
        catch(IllegalArgumentException expected) {
        } 
    }    
    
    /**
     * Shuffle
     */
    public void testShuffle() {
        Integer[] testArray = {5,4,3,2,1,9};
        int beforeCount = testArray.length;
        
        System.out.println("BEFORE");
        NumberArrays.printArray(testArray);
        NumberArrays.shuffle(testArray);
        assertTrue(beforeCount==testArray.length);
        System.out.println("AFTER");
        NumberArrays.printArray(testArray);
    }
    
    public void testLongestMonotonicSequenceNone() {
        int[] testArray = {5,4,3,2,1,9};      
        int ret =   NumberArrays.lengthOfLongestMonotonicSeries(testArray);
        assertEquals(1,ret);
    }

    public void testLongestMonotonicSequenceAll() {
        int[] testArray = {1,2,3,4,5};      
        int ret =  NumberArrays.lengthOfLongestMonotonicSeries(testArray);
       
        assertEquals(ret,testArray.length);
    }
    
    
    public void testDutchNationalFlagBasic() {       
        int[] testArray = {0,0,1,0,1,2,2,1,2,1,0};     
        NumberArrays.dutchNationalFlagBasic(testArray);
        NumberArrays.printArray(testArray);  
        assertTrue(inOrder(testArray));
    }
    
    private boolean inOrder(int[] testArray) {
        
        boolean retVal = true;
       for(int i=0; i<testArray.length-1; i++) {
           int a = testArray[i];
           int b = testArray[i+1];
           if((a == b) || (a+1 == b))
               continue;
           else
              retVal=false;
       }
       return retVal;
    }

    public void testDutchNationalFlagOnePass() {       
        int[] testArray = {0,0,1,0,1,2,2,1,2,1,0};     
        int originalLength = testArray.length;
        NumberArrays.dutchNationalFlagOnePass_Order1AdditionalSpace(testArray,1);      
        NumberArrays.printArray(testArray);
        assertTrue(originalLength==testArray.length);
        assertTrue(inOrder(testArray));
    }  
  
    public void testDutchNationalFlagOnePass_worstcase() {       
        int[] testArray = {2,2,2,2,1,1,1,1,1,0,0,0,0};     
        int originalLength = testArray.length;
        NumberArrays.dutchNationalFlagOnePass_Order1AdditionalSpace(testArray,1);      
        NumberArrays.printArray(testArray);
        assertTrue(originalLength==testArray.length);
        assertTrue(inOrder(testArray));
    } 
    public void testDutchNationalFlagOnePass_worstcase2() {       
        int[] testArray = {2,2,2,2,1,1,1,1,1,0};     
        int originalLength = testArray.length;
        NumberArrays.dutchNationalFlagOnePass_Order1AdditionalSpace(testArray,1);      
        NumberArrays.printArray(testArray);
        assertTrue(originalLength==testArray.length);
        assertTrue(inOrder(testArray));
    } 
    
    public void testCountingSort() {
    	 int[] testArray = {0,0,1,0,1,2,2,1,2,1,0};
    	 int[] answer = NumberArrays.countingSort(testArray, 2);
    	 
    	 assertTrue(answer.length==testArray.length);
         assertTrue(inOrder(answer));
         assertTrue(Arrays.equals(answer, new int[] {0,0,0,0,1,1,1,1,2,2,2}));
    	 
    }
    
    public void testCountBinaryOnes() {
        assertEquals(1, NumberArrays.countBinaryOnes(2));
        assertEquals(2, NumberArrays.countBinaryOnes(3));
        assertEquals(1, NumberArrays.countBinaryOnes(4));
        assertEquals(2, NumberArrays.countBinaryOnes(5));
        assertEquals(3, NumberArrays.countBinaryOnes(7));     
    }
    
    public void testPowerSet() {
        int[] testArray = {1,2,3};
        
        List<Set<Integer>> listOfSets = NumberArrays.powerSet(testArray);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals((double)listOfSets.size(),Math.pow(2,testArray.length));
        System.out.println("There were "+listOfSets.size()+" sets in the powerset");
    }
    
    
    
    public void testPowerSet2() {
        int[] testArray = {1,2,3,4,5,6,7,8,9,10};
        
        List<Set<Integer>> listOfSets = NumberArrays.powerSet(testArray);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals((double)listOfSets.size(),Math.pow(2,testArray.length));
        System.out.println("There were "+listOfSets.size()+" sets in the powerset");
    }

    public void testSetsOfSizeK() {
        int[] testArray = {1,2,3};
        
        List<Set<Integer>> listOfSets = NumberArrays.setsOfSizeK(testArray,2);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals(3,listOfSets.size());
        System.out.println("There were "+listOfSets.size()+" sets of size K");
    }
    
    
    
    public void testSetsOfSizeK2() {
        int[] testArray = {1,2,3,4,5,6,7,8,9,10};
        
        //One set of size 10
        List<Set<Integer>> listOfSets = NumberArrays.setsOfSizeK(testArray,10);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals(1,listOfSets.size());
        System.out.println("There were "+listOfSets.size()+" sets of size K");
        
        //10 sets of size 9
        listOfSets = NumberArrays.setsOfSizeK(testArray,9);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals(10,listOfSets.size());
        System.out.println("There were "+listOfSets.size()+" sets of size K");        
    }
    
    public void testSetsOfSizeK1() {
        int[] testArray = {1};
        
        List<Set<Integer>> listOfSets = NumberArrays.setsOfSizeK(testArray,1);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals(1,listOfSets.size());
        System.out.println("There were "+listOfSets.size()+" sets of size K");
    }
    
    public void testSetsOfSizeK0Fail() {
        int[] testArray = {1};
        
        List<Set<Integer>> listOfSets = NumberArrays.setsOfSizeK(testArray,2);             
        NumberArrays.printPowerSet(listOfSets);
        assertEquals(0,listOfSets.size());
        System.out.println("There were "+listOfSets.size()+" sets of size K");
    }
    public void testGetBinary() {
        String s = NumberArrays.getBinaryRepresentation(2);
        assertEquals("10", s);

        s = NumberArrays.getBinaryRepresentation(3);
        assertEquals("11", s);

        s = NumberArrays.getBinaryRepresentation(7);
        assertEquals("111", s);

        s = NumberArrays.getBinaryRepresentation(1024);
        assertEquals("10000000000", s);

        s = NumberArrays.getBinaryRepresentation(Integer.MAX_VALUE);
        assertEquals("1111111111111111111111111111111", s);
        assertEquals(31, s.length());
    }
    
    
    public void testGetSquareRoot() {

        double maxDiff = 0;
        for (int i = 1; i < 1000 * 1000; i++) {
            double d = NumberArrays.calcSquareRoot(i);
            double actual = Math.sqrt(i);
            double diff = Math.abs(actual - d);
            if (diff > maxDiff) {
                maxDiff = diff;
                System.out.printf("Max diff was %1.9f for Sqrt of %d \n", maxDiff, i);
            }
        }
    }

    public void testGetSquareRoot2() {
        NumberArrays.calcSquareRoot(16.0);
    }
    
    
    public void testFindCombinationsToReachTargetSum() {
        int target=6;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(4);
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);
        
        List<List<Integer>> ret = NumberArrays.findCombosToReachTargetSum(numbers, target);       
        assertNotNull(ret);
        assertEquals(2,ret.size());
        
        assertEquals(2,ret.get(0).size()); //4+2
        assertEquals(3,ret.get(1).size()); //3+2+1
    }
    
    public void testFindCombinationsToReachTargetSum2() {
        int target=10;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(4);
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);
        
        List<List<Integer>> ret = NumberArrays.findCombosToReachTargetSum(numbers, target);  
        assertNotNull(ret);
        assertEquals(1,ret.size());
        assertEquals(4,ret.get(0).size()); //4+3+2+1
    }
    
    
    public void testFindCombinationsToReachTargetEdge() {
        int target=8;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        
        List<List<Integer>> ret = NumberArrays.findCombosToReachTargetSum(numbers, target);  
        assertNotNull(ret);
        assertEquals(1,ret.size());
        assertEquals(4,ret.get(0).size()); //2+2+2+2
    }
  
    public void testFindCombinationsToReachTargetEdge3() {
        int target=6;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(2);
        numbers.add(4);
        numbers.add(4);
        numbers.add(2);
        
        List<List<Integer>> ret = NumberArrays.findCombosToReachTargetSum(numbers, target);  
        assertNotNull(ret);
        assertEquals(4,ret.size());
        assertEquals(2,ret.get(0).size()); //4+2 (four times)
    }
    
    public void testFindCombinationsToReachTargetEdge2() {
        int target=6;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        
        List<List<Integer>> ret = NumberArrays.findCombosToReachTargetSum(numbers, target);  
        assertNotNull(ret);
        assertEquals(4,ret.size());
        assertEquals(3,ret.get(0).size()); //2+2+2 in 4 combinations
    }
   
    public void testFindCombinationsToReachTargetSum3() {
        int target=11;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(4);
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);
        
        List<List<Integer>> ret =  NumberArrays.findCombosToReachTargetSum(numbers, target);  
        assertNotNull(ret);
        assertEquals(0,ret.size());
    }
    
    
    public void testFindMinMaxEven() {
        int MAX = 10000;
        int[] test = new int[MAX];
        for(int i=0; i< MAX; i++) {
            test[i]=(int)(Math.random() * (MAX + 1));
        }
        
        MinMax ans = NumberArrays.getMinMax(test);
        Arrays.sort(test);
        assertEquals(test[0],ans.min);
        assertEquals(test[test.length-1],ans.max);
        System.out.println(ans.comparisons +" comparisons performed");
        assertEquals(3*MAX/2 +1,ans.comparisons);
    }
    
    public void testFindMinMaxOdd() {
        int MAX = 99;
        int[] test = new int[MAX];
        for(int i=0; i< MAX; i++) {
            test[i]=(int)(Math.random() * (MAX + 1));
        }
        
        MinMax ans = NumberArrays.getMinMax(test);
        Arrays.sort(test);
        assertEquals(test[0],ans.min);
        assertEquals(test[test.length-1],ans.max);
        System.out.println(ans.comparisons +" comparisons performed");
        assertEquals(3*MAX/2,ans.comparisons);
    }
  
    public void testFindMinMaxEdge() {
        int[] test = new int[] {2,1,0};

        
        MinMax ans = NumberArrays.getMinMax(test);
        Arrays.sort(test);
        assertEquals(test[0],ans.min);
        assertEquals(test[test.length-1],ans.max);
        System.out.println(ans.comparisons +" comparisons performed");
        assertEquals(4,ans.comparisons);
    }
    
    public void testFindMinMaxEdge2() {
        int[] test = new int[] {2,1};

        
        MinMax ans = NumberArrays.getMinMax(test);
        Arrays.sort(test);
        assertEquals(test[0],ans.min);
        assertEquals(test[test.length-1],ans.max);
        System.out.println(ans.comparisons +" comparisons performed");
        assertEquals(1,ans.comparisons);
    }
    
    
    public void testFindMinMaxEdge3() {
        int[] test = new int[] {1,2,3,4,5,6,7,0,8}; //Odd number of elements
        
        MinMax ans = NumberArrays.getMinMax(test);
        assertEquals(test[test.length-2],ans.min);
        assertEquals(test[test.length-1],ans.max);
        System.out.println(ans.comparisons +" comparisons performed");
        assertEquals(13,ans.comparisons);
    }
    
    public void testFindMinMaxEdge4() {
        int[] test = new int[] {2,3,4,5,6,7,0,8}; //Even number
         
        MinMax ans = NumberArrays.getMinMax(test);
        assertEquals(test[test.length-2],ans.min);
        assertEquals(test[test.length-1],ans.max);
        System.out.println(ans.comparisons +" comparisons performed");
        assertEquals(13,ans.comparisons);
    }
    
    public void testMerge() {
        int[] a = new int[] {1,2,3,4,5,10};
        int[] b = new int[] {6,7,8,9};
        
        int[] merge = NumberArrays.merge(a, b);
        assertEquals(a.length+b.length,merge.length);
        String s = Arrays.toString(merge);
        System.out.println(s);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",s);     
    }
    
    public void testMerge2() {
        int[] a = new int[] {1,2,3,4,5,10};
        int[] b = new int[] {6,7,8,9,10};
        
        int[] merge = NumberArrays.merge(a, b);
        assertEquals(a.length+b.length,merge.length);
        String s = Arrays.toString(merge);
        System.out.println(s);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10]",s);     
    } 
  
    
    public void testMerge3() {
        int[] a = new int[] {1};
        int[] b = new int[] {2,3,4,5,6,7,8,9,10};
        
        int[] merge = NumberArrays.merge(a, b);
        assertEquals(a.length+b.length,merge.length);
        String s = Arrays.toString(merge);
        System.out.println(s);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",s);     
    } 
    
    public void testMergeEdge1() {
        int[] a = new int[] {};
        int[] b = new int[] {1,2,3,4,5,6,7,8,9,10};
        
        int[] merge = NumberArrays.merge(a, b);
        assertEquals(a.length+b.length,merge.length);
        String s = Arrays.toString(merge);
        System.out.println(s);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",s);     
    }
    
    public void testMergeEdge2() {
        int[] b = new int[] {};
        int[] a = new int[] {1,2,3,4,5,6,7,8,9,10};
        
        int[] merge = NumberArrays.merge(a, b);
        assertEquals(a.length+b.length,merge.length);
        String s = Arrays.toString(merge);
        System.out.println(s);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",s);     
    }
    
    public void testMergeRemovingDupes() {
        int[] a = new int[] {1,2,3,4,5,10};
        int[] b = new int[] {6,7,8,9,10};
        
        Integer[] merge = NumberArrays.mergeRemovingDupes(a, b);
        assertEquals((a.length+b.length)-1,merge.length);
        String s = Arrays.toString(merge);
        System.out.println(s);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",s);     
    } 
    
    
    public void testFindIndexOfItemInSortedArray() {
        int[] a = new int[] {-14,-10,2,108,108,243,285,285,285,401 };
        
        assertEquals(3,NumberArrays.findFirstOccurrenceOfItemInSortedArray(a, 108));
        assertEquals(6,NumberArrays.findFirstOccurrenceOfItemInSortedArray(a, 285));
    }
    
    public void testFindKthSmallestInTwoArrays() {
    	int[] a = new int[] { 1,2,3,4,5 };
    	int[] b = new int[] { 6,7,8,9,10 };
    	int i=0;
   
    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 1);
    	assertEquals(1,i);
    	
    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 2);
    	 assertEquals(2,i);

    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 6);
    	 assertEquals(6,i);
    	 
    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 10);
    	 assertEquals(10,i);
    }
    
    public void testFindKthSmallestInTwoArraysOverlapping() {
    	int[] a = new int[] { 1,3,5,7,9 };
    	int[] b = new int[] { 2,4,6,8,10 };
    	int i=0;
   
    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 1);
    	assertEquals(1,i);
    	
    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 2);
    	 assertEquals(2,i);

    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 6);
    	 assertEquals(6,i);
    	 
    	 i = NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 10);
    	 assertEquals(10,i);
    }
    
	public void testFindKthSmallestInTwoArraysErrors() {
		int[] a = new int[] { 1, 2, 3, 4, 5 };
		int[] b = new int[] { 6, 7, 8, 9, 10 };
		try {
			NumberArrays.getKthSmallestElementInTwoSortedArrays(a, b, 11);
			fail("Exception expected");
		}catch(IllegalArgumentException iae) {
			assertTrue(true);
		}
	}
	
	public void testContainsDuplicates1() {
        int[] a = new int[] {-14,-10,2,108,108,243,285,285,285,401 };
        
        assertTrue(NumberArrays.containsDuplicates_orderNspace(a));
    }
	
	public void testContainsDuplicates2() {
        int[] a = new int[] {-14,-10,2,108,109,243,285,286,108,401 };
        
        assertTrue(NumberArrays.containsDuplicates_orderNspace(a));
    }
	
	
	public void testContainsDuplicates2False() {
        int[] a = new int[] {-14,-10,2,108,109,243,285,286,287,401 };
        
        assertFalse(NumberArrays.containsDuplicates_orderNspace(a));
    }
	
	public void testContainsDuplicates_order1space_1() {
        int[] a = new int[] { 1,2,2,3,4,5,6,7,8,9 };
        
        assertTrue(NumberArrays.containsDuplicates_order1space(a));
    }
	
	public void testContainsDuplicates_order1space_2() {
        int[] a = new int[] { 1,2,3,4,5,6,7,8,9, 9};
        
        assertTrue(NumberArrays.containsDuplicates_order1space(a));
    }
	
	public void testContainsDuplicates_order1space_fail() {
        int[] a = new int[] { 0, 1,2,3,4,5,6,7,8,9};
        
        assertFalse(NumberArrays.containsDuplicates_order1space(a));
    }
	
	public void testFindMostFrequentElem() {
		int[] a = new int[] { 0, 1,2,3,4,5,6,7,8,9};
		int i = NumberArrays.findMostFrequentlyOccurringElement(a);
		assertEquals(0,i);
	}
	
	public void testFindMostFrequentElem1() {
		int[] a = new int[] { 0, 1,2,3,4,5,6,7,8,0};
		int i = NumberArrays.findMostFrequentlyOccurringElement(a);
		assertEquals(0,i);
	}
	
	public void testFindMostFrequentElem2() {
		int[] a = new int[] { 0, 1,2,3,4,5,7,7,7,0};
		int i = NumberArrays.findMostFrequentlyOccurringElement(a);
		assertEquals(7,i);
	}
	
	public void testFindMostFrequentElem3() {
		int[] a = new int[] { 0, 1,2,3,4,5,-7,-7,-7,0};
		int i = NumberArrays.findMostFrequentlyOccurringElement(a);
		assertEquals(-7,i);
	}
	
	public void testFindMissingNumber() {
		int[] a = new int[] { 1,2,4,6,3,7,8};
		int i = NumberArrays.findMissingNumber(a);
		assertEquals(5,i);
	}
}
