package com.kellyfj.codingkata.trees;

import junit.framework.TestCase;

public class BinaryTreeNodeTest extends TestCase {
    private BinaryTreeNode bt = null;
    private BinaryTreeNode bt100Left=null;
    private BinaryTreeNode bt100Balanced=null;
    
    @Override
    protected void setUp() throws Exception {
        bt = new BinaryTreeNode(1);
        bt.setLeft(new BinaryTreeNode(2));
        bt.setRight(new BinaryTreeNode(3));
        
        BinaryTreeNode btNext=null;
        BinaryTreeNode btTop = new BinaryTreeNode(0);
        BinaryTreeNode btCurrent = btTop;
        for(int i=1; i< 100; i++)
        {
            btNext = new BinaryTreeNode(i);
            btCurrent.setLeft(btNext);
            btCurrent = btNext;
        }
        bt100Left = btTop;

        btNext=null;
        btTop = new BinaryTreeNode(0);
        btCurrent = btTop;
        //Add 50 to the left
        for(int i=1; i< 50; i++)
        {
            btNext = new BinaryTreeNode(i);
            btCurrent.setLeft(btNext);
            btCurrent = btNext;
        }
        //Add 50 to the right
        btCurrent=btTop;
        for(int i=51; i<100; i++)
        {
            btNext = new BinaryTreeNode(i);
            btCurrent.setRight(btNext);
            btCurrent = btNext; 
        }
        bt100Balanced = btTop;
    }

    public void testSize()
    {
        assertTrue(bt.getSize()==3);
        
        assertEquals(100, bt100Left.getSize());
        assertEquals(100, bt100Left.getHeightOfTree());
        assertEquals(99, bt100Left.getDeepestNode().getValue());
        assertEquals(true,bt100Left.isStructurallySame(bt100Left));
        int sum = bt100Left.getSumOfAllLeafValues();
        assertEquals(4950,sum);
        
        assertEquals(99,bt100Balanced.getSize());
        assertEquals(50,bt100Balanced.getHeightOfTree());
        assertEquals(99, bt100Balanced.getDeepestNode().getValue());
        assertEquals(true,bt100Balanced.isStructurallySame(bt100Balanced));
        assertFalse(bt100Left.isStructurallySame(bt100Balanced));
        assertFalse(bt100Balanced.isStructurallySame(bt100Left));
        sum = bt100Balanced.getSumOfAllLeafValues();
        assertEquals(4900,sum);
    }
    
    public void testSerialize() {
        
        String s = bt.serialize();
        assertNotNull(s);
        assertEquals("1,N;2,L;3,L;",s);
        
        BinaryTreeNode deser = BinaryTreeNode.deserialize(s);
        int size = deser.getSize();
        assertEquals(3,size);
        
        assertEquals(bt,deser);
        assertEquals(deser,bt);
        assertNotSame(bt, bt100Left);
        assertNotSame(bt100Left,bt);
        
        assertEquals(bt100Left,bt100Left);
        assertEquals(bt100Balanced,bt100Balanced);
        String s2 = bt100Left.serialize();
        assertNotNull(s2);
        assertFalse(s2.trim().equals(""));
        String s3 = bt100Balanced.serialize();
        assertNotNull(s3);
        assertFalse(s3.trim().equals(""));
    }
}
