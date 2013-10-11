package com.kellyfj.codingkata.matrix;

import junit.framework.TestCase;

public class MatrixUtilsTest extends TestCase {

    public void testRotateMatrix() {
      int[][] testMatrix = { { 1, 2 , 3}, {4, 5, 6} };
      
     System.out.println("Rotate 90 Test");
     System.out.println("BEFORE");
     MatrixUtils.printMatrix(testMatrix); 
     
     int[][] resultMatrix= MatrixUtils.rotateMatrix90(testMatrix);
     System.out.println("AFTER");
     MatrixUtils.printMatrix(resultMatrix);
     
     assertEquals(testMatrix.length,resultMatrix[0].length);
     assertEquals(testMatrix[0].length, resultMatrix.length);
     
    }
   
    public void testTransposeMatrix() {
        int[][] testMatrix = { { 1, 2 , 3}, {4, 5, 6}, {7,8,9}};

        System.out.println("Transpose Test");
        System.out.println("BEFORE");
       MatrixUtils.printMatrix(testMatrix); 
       
       int[][] resultMatrix= MatrixUtils.transpose(testMatrix);

       System.out.println("AFTER");
       MatrixUtils.printMatrix(resultMatrix);
       
       assertEquals(testMatrix.length,resultMatrix[0].length);
       assertEquals(testMatrix[0].length, resultMatrix.length);
       
      }
}
