package com.kellyfj.codingkata.trees;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class BinaryTreeUtilsTest extends TestCase {

    private BinarySearchTreeNode bst=null;
    private BinarySearchTreeNode smallBst=null;
    private BinarySearchTreeNode smallBst2=null;
    
    //For symmetric tests
    private BinaryTreeNode small_122 = null;
    private BinaryTreeNode small_1_22_3 = null;
    private BinaryTreeNode small_1_22_4334 = null;
    private BinaryTreeNode small_1_22_3434 = null;
    private BinaryTreeNode small_1_22_33 = null;
    		
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
        
        setupSymmetricTrees();
    }

    private void setupSymmetricTrees() {
    	/*   1
 			/ \
		   2   2 	 */
    	small_122 = new BinaryTreeNode(1);
    	small_122.setLeft(new BinaryTreeNode(2));
    	small_122.setRight(new BinaryTreeNode(2));    
    	
    	/*   1
 			/ \
		   2   2
		    \ 	
		     3 */
    	small_1_22_3 = new BinaryTreeNode(1);
    	small_1_22_3.setLeft(new BinaryTreeNode(2));
    	small_1_22_3.setRight(new BinaryTreeNode(2)); 
    	small_1_22_3.getRight().setRight(new BinaryTreeNode(3));
    	
    	/*   1
   		   /   \
          2     2
         / \   / \
        4   3 3   4 */
    	small_1_22_4334 = new BinaryTreeNode(1);
    	small_1_22_4334.setLeft(new BinaryTreeNode(2));
    	small_1_22_4334.setRight(new BinaryTreeNode(2)); 
    	small_1_22_4334.getLeft().setLeft(new BinaryTreeNode(4));
    	small_1_22_4334.getLeft().setRight(new BinaryTreeNode(3));
    	small_1_22_4334.getRight().setLeft(new BinaryTreeNode(3));
    	small_1_22_4334.getRight().setRight(new BinaryTreeNode(4)); 
    	
    	/*   	 1
     		   /   \
    		  2     2 
   		     / \   / \
  			3   4 3   4
    	 */
    	small_1_22_3434 = new BinaryTreeNode(1);
    	small_1_22_3434.setLeft(new BinaryTreeNode(2));
    	small_1_22_3434.setRight(new BinaryTreeNode(2)); 
    	small_1_22_3434.getLeft().setLeft(new BinaryTreeNode(3));
    	small_1_22_3434.getLeft().setRight(new BinaryTreeNode(4));
    	small_1_22_3434.getRight().setLeft(new BinaryTreeNode(3));
    	small_1_22_3434.getRight().setRight(new BinaryTreeNode(4));
    	
    	/*    1
     		/   \
    	   2     2
   		  /       \
  		 3         3 */
    	small_1_22_33 = new BinaryTreeNode(1);
    	small_1_22_33.setLeft(new BinaryTreeNode(2));
    	small_1_22_33.setRight(new BinaryTreeNode(2)); 
    	small_1_22_33.getLeft().setLeft(new BinaryTreeNode(3));
    	small_1_22_33.getRight().setRight(new BinaryTreeNode(3));
    	
    }
    @Test
    public void testBreadthFirstTraverse() {
        String str = BinaryTreeUtils.bfsTraverse(smallBst);
        System.out.println(str);
        assertEquals(str, "4, \n" + "2, 6, \n" + "1, 3, 5, 7, \n");
    }

    @Test
    public void testBreadthFirstTraverse2() {
        String str = BinaryTreeUtils.bfsTraverse(smallBst2);
        System.out.println(str);
        assertEquals(str, "5, \n" + "2, 8, \n" + "1, 3, 6, 9, \n" + "4, 7, 10, \n");
    }
    
    
    @Test
    public void testBreadthFirstTraverseLarge() {
        String str = BinaryTreeUtils.bfsTraverse(bst);
        System.out.println(str);
        assertEquals(
                str,
                "50, \n"
                        + "25, 75, \n"
                        + "12, 37, 62, 88, \n"
                        + "6, 18, 31, 43, 56, 68, 81, 94, \n"
                        + "3, 9, 15, 21, 28, 34, 40, 46, 53, 59, 65, 71, 78, 84, 91, 97, \n"
                        + "1, 4, 7, 10, 13, 16, 19, 23, 26, 29, 32, 35, 38, 41, 44, 48, 51, 54, 57, 60, 63, 66, 69, 73, 76, 79, 82, 86, 89, 92, 95, 99, \n"
                        + "2, 5, 8, 11, 14, 17, 20, 22, 24, 27, 30, 33, 36, 39, 42, 45, 47, 49, 52, 55, 58, 61, 64, 67, 70, 72, 74, 77, 80, 83, 85, 87, 90, 93, 96, 98, 100, \n");
    }
   
    @Test
    public void testInOrderTraverse() {
        String s = BinaryTreeUtils.inOrder(smallBst);
        System.out.println("InOrder: "+s);
        assertEquals("1,2,3,4,5,6,7,",s);
    }

    @Test
    public void testInOrderTraverse_Order1Space() {
        String s = BinaryTreeUtils.inOrder_Order1Space(smallBst);
        System.out.println("InOrder: "+s);
        assertEquals("1,2,3,4,5,6,7,",s);
    }
    
    @Test
    public void testInOrderTraverse_Order1Space_large() {
        String s = BinaryTreeUtils.inOrder_Order1Space(bst);
        System.out.println("InOrder: "+s);
        assertTrue(s.startsWith("1,2,3,4,5,6,7,8,9,10,11,12"));
        assertTrue(s.endsWith("89,90,91,92,93,94,95,96,97,98,99,100,"));
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
        
        List<BinaryTreeNode> t = BinaryTreeUtils.findShortestPathBFS(smallBst);
        assertNotNull(t);
        printList(t);
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
        
        List<BinaryTreeNode> t = BinaryTreeUtils.findShortestPathBFS(smallBst2);
        assertNotNull(t);
        printList(t);
        assertEquals(3,l.size()); 
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
        
        List<BinaryTreeNode> t = BinaryTreeUtils.findShortestPathBFS(bst);
        assertNotNull(t);
        printList(t);
        assertEquals(6,l.size());
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
    private void printList(List<BinaryTreeNode> l) {
        for(BinaryTreeNode i : l) {
            System.out.print(i.getValue() + "-->");
        }
        System.out.println("");
    }
    public void testLowestCommonAncestor() {
        
        BinarySearchTreeNode a = new BinarySearchTreeNode(1);
        BinarySearchTreeNode b = new BinarySearchTreeNode(5);
        System.out.println(smallBst.toString());
       
        BinaryTreeNode n = BinaryTreeUtils.lowestCommonAncestor(smallBst, a, b);
        
        assertNotNull(n);
        assertEquals(4,n.getValue());
        
        
        a = new BinarySearchTreeNode(1);
        b = new BinarySearchTreeNode(100);
        System.out.println(bst.toString());
        n = BinaryTreeUtils.lowestCommonAncestor(bst, a, b);
        assertNotNull(n);
        assertEquals(50,n.getValue());        
        
    }
    
    public void testLowestCommonAncestor_Order1Space() {
        
        BinaryTreeNode a =  smallBst.search(1);
        BinaryTreeNode b =  smallBst.search(5);
        System.out.println(smallBst.toString());
        BinaryTreeNode n = BinaryTreeUtils.lowestCommonAncestor_Order1Space_OrderHTime(smallBst, a, b);
        
        assertNotNull(n);
        assertEquals(4,n.getValue());
        
        
        a = bst.search(1);
        b = bst.search(100);
        System.out.println(bst.toString());
        n = BinaryTreeUtils.lowestCommonAncestor_Order1Space_OrderHTime(bst, a, b);
        assertNotNull(n);
        assertEquals(50,n.getValue());        
        
    }
    
    public void testDFS() {
        assertTrue(BinaryTreeUtils.dfsTraverse(smallBst, 5));
        
        assertTrue(BinaryTreeUtils.dfsTraverse(bst, 100));
    }
    
    public void testSymmetric() {
    	assertTrue(BinaryTreeUtils.isSymmetric_aka_isMirror(small_122));
    	assertFalse(BinaryTreeUtils.isSymmetric_aka_isMirror(small_1_22_3));
    	assertTrue(BinaryTreeUtils.isSymmetric_aka_isMirror(small_1_22_4334));
    	assertFalse(BinaryTreeUtils.isSymmetric_aka_isMirror(small_1_22_3434));
    	assertTrue(BinaryTreeUtils.isSymmetric_aka_isMirror(small_1_22_33));
    }
}
