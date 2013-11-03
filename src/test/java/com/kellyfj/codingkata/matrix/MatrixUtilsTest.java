package com.kellyfj.codingkata.matrix;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatrixUtilsTest {

    @Test
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
   
    @Test
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
    
    @Test
    public void testCreateMatrix() {
        boolean ans = false;
        int n = 1;
        do {
            boolean[][] matrix = MatrixUtils.createRandomMatrix(10);

            Coordinate start = new Coordinate(0, 0);
            Coordinate end = new Coordinate(9, 9);

            matrix[start.x][start.y] = true;
            matrix[end.x][end.y] = true;
            MatrixUtils.printMatrix(matrix);

            ans = MatrixUtils.isThereAPath(matrix, start, end);
            if(ans == false) 
                System.out.println("NO PATH  . . . . TRYING AGAIN");
            else    
                System.out.println("FOUND PATH AFTER "+n+" TRYS");
            n++;
        } while (ans == false);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIsThereAPathError() {

        boolean[][] matrix = MatrixUtils.createRandomMatrix(10);

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(10, 10);

        MatrixUtils.isThereAPath(matrix, start, end);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testIsThereAPathError2() {

        boolean[][] matrix = MatrixUtils.createRandomMatrix(10);

        Coordinate start = new Coordinate(-1, -1);
        Coordinate end = new Coordinate(5, 5);

        MatrixUtils.isThereAPath(matrix, start, end);
    }
}
