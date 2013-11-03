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
    
    public static boolean[][] createRandomMatrix(int size) {
        boolean[][] matrix = new boolean[size][size];
        for(int i=0; i< size; i++) {
            for(int j=0; j< size; j++) {
                matrix[i][j] =  Math.random() < 0.5;
            }
        }
        
        return matrix;
    }
    
    public static void printMatrix(boolean[][] matrix) {
        int xsize = matrix.length;
        int ysize = matrix[0].length;

        //Print Top line
        for (int j = 0; j < ysize; j++) {
            System.out.print("___");
        }
        System.out.println("_"); 
            
        //Print Matrix Values
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {

                    System.out.print("| ");
                
                if (matrix[i][j])
                    System.out.print(" ");
                else
                    System.out.print("X");
            }
            System.out.println("|");
        }
        
        //Print bottom line
        for (int j = 0; j < ysize; j++) {
            System.out.print("---");
        }
        System.out.println("-");  
    }

    public boolean isThereAPath(boolean[][] matrix, int x1, int y1, int x2, int y2) {
        if(x1<0 || x1 > matrix.length)
            throw new IllegalArgumentException("x1 is out of bounds");
        if(y1<0 || y1 > matrix[0].length)
            throw new IllegalArgumentException("y1 is out of bounds");
        if(x2<0 || x2 > matrix.length)
            throw new IllegalArgumentException("x2 is out of bounds");
        if(y2<0 || y2 > matrix[0].length)
            throw new IllegalArgumentException("y2 is out of bounds");   
        
        return true;
    }
}
