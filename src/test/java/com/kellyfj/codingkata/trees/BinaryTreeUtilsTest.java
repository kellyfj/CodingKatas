package com.kellyfj.codingkata.trees;

import junit.framework.TestCase;

import org.junit.Test;

public class BinaryTreeUtilsTest extends TestCase {

    private BinarySearchTreeNode bst=null;
    private BinarySearchTreeNode smallBst=null;
    
    @Override
    protected void setUp() throws Exception {
        
        int[] testArray = new int[100];
        for(int i=1; i<= 100; i++)
        {
            testArray[i-1]=i;
        }
        
        bst = BinarySearchTreeNode.fromArray(testArray);
 
        int[] testArray2 = new int[5];
        for(int i=1; i<= 5; i++)
        {
            testArray2[i-1]=i;
        }
        smallBst =  BinarySearchTreeNode.fromArray(testArray2);
    }
    
    @Test
    public void testBreadthFirstTraverse() {
        BinaryTreeUtils.bfsTraverse(smallBst);
    }

    @Test
    public void testBreadthFirstTraverseLarge() {
        BinaryTreeUtils.bfsTraverse(bst);
    }
}
