package com.kellyfj.codingkata.dp;

import java.util.ArrayList;
import java.util.List;

public class Partition {

    private static final List<Integer> listA= new ArrayList<Integer>();
    private static final List<Integer> listB= new ArrayList<Integer>();
    
    private static void printLists()
    {
        System.out.print("List A = {");
        for(int i=0; i< listA.size(); i++)
        {
            System.out.print(listA.get(i)+",");
        }
        System.out.println("}");
        System.out.print("List b = {");
        for(int i=0; i< listB.size(); i++)
        {
            System.out.print(listB.get(i)+",");
        }
        System.out.println("}");
    }
    
    /**
     * GOAL: Given a set of positive integers, partition the set into two such that 
     * the difference between the sum of the two sets is minimized.
     * 
     * Code inspired by <link>http://www.ugrad.cs.ubc.ca/~cs490/sec202/notes/dp/sample.html</link>
     * See also http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
     * 
     * @param input
     * @param startIndex
     * @param tryToMake
     * @return
     */
    public static int getBalancedPartition(int input[], int startIndex, int tryToMake)
    {
      if(startIndex==input.length-1) { 
          listA.add(input[startIndex]);
          return input[startIndex];
      } else {
      int sum=0;
      if(tryToMake==0){
          for(int i=0; i< input.length; i++)
              sum+=input[i];
          tryToMake=sum;
      }
      
      //Arrays.sort(input);
      int ans1 = getBalancedPartition(input,startIndex+1, tryToMake-input[startIndex]) + input[startIndex];
      int ans2 = getBalancedPartition(input,startIndex+1, tryToMake+input[startIndex]) - input[startIndex];
      int diff1 = Math.abs(tryToMake - ans1);
      int diff2 = Math.abs(tryToMake - ans2);
      if(diff1 < diff2) {
          listA.add(input[startIndex]);
          return ans1;
      }
       else {
          listB.add(input[startIndex]);
          return ans2;
       }
      }
      
    }
    
    public static boolean isThereAPartition (int arr[])
    {
        int sum = 0;
        
        int n = arr.length;
        // Caculcate sun of all elements
        for (int i = 0; i < n; i++)
          sum += arr[i];
        
        if (sum%2 != 0)  
           return false;
        
        int targetSum = sum/2;
      
        boolean part[][] = new boolean [targetSum+1][n+1];
        
        // initialize top row as true
        for (int i = 0; i <= n; i++)
          part[0][i] = true;
          
        // initialize leftmost column, except part[0][0], as false
        for (int i = 1; i <= targetSum; i++)
          part[i][0] = false;     
         
         // Fill the partition table in bottom up manner 
         for (int i = 1; i <= targetSum; i++)  
         {
           for (int j = 1; j <= n; j++)  
           {
             part[i][j] = part[i][j-1];
             if (i >= arr[j-1])
               part[i][j] = part[i][j] || part[i - arr[j-1]][j-1];
           }        
         }    
         
         // uncomment this part to print table 
         for (int i = 0; i <= targetSum; i++)  
         {
           for (int j = 0; j <= n; j++)  
              System.out.print(part[i][j]+" ");
           System.out.println("");
         }  
         
         return part[sum/2][n];
    }     

    // Driver program to test above funtion
    public static void main(String[] args)
    {
      int arr[] = {1,2,3};
      if (isThereAPartition(arr) == true)
         System.out.println("Can be divided into two subsets of equal sum");
      else
          System.out.println("Cannot be divided into two subsets of equal sum");
      
      
      getBalancedPartition(arr,0,0);
      printLists();
    }
}
