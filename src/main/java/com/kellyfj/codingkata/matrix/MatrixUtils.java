package com.kellyfj.codingkata.matrix;

public class MatrixUtils {

    public static int[][] rotateMatrix90(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rot = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // rotateM[j][i] = m[m.length-i-1][j];
                rot[j][i] = matrix[rows - i - 1][j];
            }
        }
        return rot;
    }
    
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rot = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // rotateM[j][i] = m[m.length-i-1][j];
                rot[j][i] = matrix[i][j];
            }
        }
        return rot;
    }  

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("}");
        }

    }
    
    public int getOriginalColor(boolean[][] carpet)  {
        if(carpet.length != carpet[0].length)
            throw new IllegalArgumentException("Argument needs to be NxN square");
        
        int white=countRegions(carpet,false);
        int black=countRegions(carpet,true);

        //return 0 for white, 1 for black, 2 for tie
        if(white>black)
            return 0;
        else if (white < black)
            return 1;
        else
            return 2;
    }

    private int countRegions(boolean[][] carpet, boolean b) {
        // TODO Auto-generated method stub
        return 0;
    }
}
