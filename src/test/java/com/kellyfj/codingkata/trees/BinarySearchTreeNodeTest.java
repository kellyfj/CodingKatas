package com.kellyfj.codingkata.trees;

import junit.framework.TestCase;

public class BinarySearchTreeNodeTest extends TestCase {

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

    public void testSize()
    {
               
        assertEquals(100, bst.getSize());
    }
    
    public void testGetKthSmallest() {
        BinaryTreeNode n = bst.getKthSmallestElement(1);
        assertNotNull(n);
        assertEquals(1,n.getValue());
        
         n = bst.getKthSmallestElement(100);
        assertNotNull(n);
        assertEquals(100,n.getValue());
    }
    
    public void testGetKthLargest() {
        BinaryTreeNode n = bst.getKthLargestElement(1);
        assertNotNull(n);
        assertEquals(100,n.getValue());
        
         n = bst.getKthLargestElement(1);
        assertNotNull(n);
        assertEquals(100,n.getValue());
    }
    
    public void testLowestCommonAncestor() {
        
        BinarySearchTreeNode a = new BinarySearchTreeNode(1);
        BinarySearchTreeNode b = new BinarySearchTreeNode(5);
        System.out.println(smallBst.toString());
        BinarySearchTreeNode n = BinarySearchTreeNode.lowestCommonAncestor(smallBst, a, b);
        
        assertNotNull(n);
        assertEquals(3,n.getValue());
        
        
        a = new BinarySearchTreeNode(1);
        b = new BinarySearchTreeNode(100);
        System.out.println(bst.toString());
        n = BinarySearchTreeNode.lowestCommonAncestor(bst, a, b);
        assertNotNull(n);
        assertEquals(50,n.getValue());        
        
    }
}
