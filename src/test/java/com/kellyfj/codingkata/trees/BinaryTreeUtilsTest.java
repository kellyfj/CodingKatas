package com.kellyfj.codingkata.trees;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class BinaryTreeUtilsTest extends TestCase {

    private BinarySearchTreeNode bst=null;
    private BinarySearchTreeNode smallBst=null;
    private BinarySearchTreeNode smallBst2=null;
    
    @Override
    protected void setUp() throws Exception {
        
        int[] testArray = new int[100];
        for(int i=1; i<= 100; i++)
        {
            testArray[i-1]=i;
        }
        
        bst = BinarySearchTreeNode.fromArray(testArray);
 
        int[] testArray2 = new int[7];
        for(int i=1; i<= 7; i++)
        {
            testArray2[i-1]=i;
        }
        smallBst =  BinarySearchTreeNode.fromArray(testArray2);
        
        int[] testArray3 = new int[10];
        for(int i=1; i<= 10; i++)
        {
            testArray3[i-1]=i;
        }
        smallBst2 =  BinarySearchTreeNode.fromArray(testArray3);
    }
    
    @Test
    public void testBreadthFirstTraverse() {
        BinaryTreeUtils.bfsTraverse(smallBst);
    }

    @Test
    public void testBreadthFirstTraverse2() {
        BinaryTreeUtils.bfsTraverse(smallBst2);
    }
    
    
    @Test
    public void testBreadthFirstTraverseLarge() {
        BinaryTreeUtils.bfsTraverse(bst);
    }
   
    @Test
    public void testInOrderTraverse() {
        String s = BinaryTreeUtils.inOrder(smallBst);
        System.out.println("InOrder: "+s);
        assertEquals("1,2,3,4,5,6,7,",s);
    }

    @Test
    public void testPreOrderTraverse() {
        String s = BinaryTreeUtils.preOrder(smallBst);
        System.out.println("PreOrder: "+s);
        assertEquals("4,2,1,3,6,5,7,",s);
    }

    @Test
    public void testPostOrderTraverse() {
        String s = BinaryTreeUtils.postOrder(smallBst);
        System.out.println("PostOrder: "+s);
        assertEquals("1,3,2,5,7,6,4,",s);
    }
    
    @Test
    public void testGetShortestPath() {
        List<Integer> l = BinaryTreeUtils.findShortestPath(smallBst);
        assertNotNull(l);
        printIntList(l);
        assertEquals(3,l.size());        
    }
    
    @Test
    public void testGetLongestPath() {
        List<Integer> l = BinaryTreeUtils.findLongestPath(smallBst);
        assertNotNull(l);
        printIntList(l);
        assertEquals(3,l.size());        
    }
    
    @Test
    public void testGetShortestPath2() {
        List<Integer> l = BinaryTreeUtils.findShortestPath(smallBst2);
        assertNotNull(l);
        assertEquals(3,l.size()); 
        printIntList(l);
    }
    
    @Test
    public void testGetLongestPath2() {
        List<Integer> l = BinaryTreeUtils.findLongestPath(smallBst2);
        assertNotNull(l);
        printIntList(l);
        assertEquals(3,l.size()); 
        printIntList(l);
    }

    @Test
    public void testGetShortestPath3() {
        List<Integer> l = BinaryTreeUtils.findShortestPath(bst);
        assertNotNull(l);
        printIntList(l);
        assertEquals(6,l.size()); //floor (log2(N)) 
    }


    @Test
    public void testGetLongestPath3() {
        List<Integer> l = BinaryTreeUtils.findLongestPath(bst);
        assertNotNull(l);
        printIntList(l);
        assertEquals(7,l.size()); //ceil (log2(N)) 
    }
 
    
    private void printIntList(List<Integer> l) {
        for(Integer i : l) {
            System.out.print(i + "-->");
        }
        System.out.println("");
    }
}
