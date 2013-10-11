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
}
