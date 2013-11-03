package com.kellyfj.codingkata.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        // Print Top line
        for (int j = 0; j < ysize; j++) {
            System.out.print("___");
        }
        System.out.println("_");

        // Print Matrix Values
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

        // Print bottom line
        for (int j = 0; j < ysize; j++) {
            System.out.print("---");
        }
        System.out.println("-");
    }

    public static boolean isThereAPath(boolean[][] matrix, Coordinate start, Coordinate end) {
        if(start.x<0 || start.x >= matrix.length)
            throw new IllegalArgumentException("x1 is out of bounds");
        if(start.y<0 || start.y >= matrix[0].length)
            throw new IllegalArgumentException("y1 is out of bounds");
        if(end.x<0 || end.x >= matrix.length)
            throw new IllegalArgumentException("x2 is out of bounds");
        if(end.y<0 || end.y >= matrix[0].length)
            throw new IllegalArgumentException("y2 is out of bounds");  
        
        if(matrix[start.x][start.y]==false)
            throw new IllegalArgumentException("Start position is not a legal position");
        if(matrix[end.x][end.y]==false)
            throw new IllegalArgumentException("Start position is not a legal position");
        
        
        List<Coordinate> path = new ArrayList<Coordinate>();
        path.add(start);
        boolean retVal = isThereAPath(matrix, start, end, path);
        if(retVal==true) {
            String s = Arrays.toString(path.toArray());
            System.out.println("Path = "+s);
        }
        
        return retVal;
    }
    
    private final static int[] X_SHIFT = new int[] {0, 0, 1, -1};
    private final static int[] Y_SHIFT = new int[] {1, -1, 0, 0 };
    
    private static boolean isThereAPath(boolean[][] matrix, Coordinate start, Coordinate end, List<Coordinate> path) {
        if(start.equals(end))
            return true;
        
        //Four move choices
        for(int i=0; i<4; i++) {
            Coordinate next = new Coordinate(start.x+X_SHIFT[i],start.y+Y_SHIFT[i]);
            //System.out.print("Trying ("+next.x+","+next.y+")");
            if(!path.contains(next) && isLegalMove(matrix,next)) {
                path.add(next);
                //System.out.println(" . . . OK");
                if(isThereAPath(matrix,next,end,path)) {
                    return true;
                }
                path.remove(next);
            }
            //System.out.println(" . . . NO GOOD");
        }
        
        return false;
    }

    private static boolean isLegalMove(boolean[][] matrix, Coordinate next) {
        boolean xOK = next.x >= 0 && next.x < matrix.length;
        boolean yOK = next.y >= 0 && next.y < matrix[0].length;

        if (xOK && yOK && matrix[next.x][next.y] == true)
            return true;
        else
            return false;
    }
}
