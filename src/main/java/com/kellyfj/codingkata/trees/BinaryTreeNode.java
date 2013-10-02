package com.kellyfj.codingkata.trees;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTreeNode {
    
    private int value;
    protected BinaryTreeNode left;
    protected BinaryTreeNode right;
    
    public BinaryTreeNode(int value)
    {
        this.value=value;
    }
    
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public BinaryTreeNode getLeft() {
        return left;
    }
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }
    public BinaryTreeNode getRight() {
        return right;
    }
    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
    
    public int getSize() {
        int leftSize = left == null ? 0 : left.getSize();
        int rightSize = right == null ? 0 : right.getSize();
        return 1 + leftSize + rightSize;
    }

    public int getDepth()
    {
        int leftDepth = left == null ? 0 : left.getDepth();
        int rightDepth = right == null ? 0 : right.getDepth();
        
        if(leftDepth > rightDepth)
            return leftDepth+1;
        else
            return rightDepth +1;
    }
    
    public BinaryTreeNode getDeepestNode()
    {
        Queue<BinaryTreeNode> queue = new LinkedBlockingQueue<BinaryTreeNode>();
        queue.add(this);
        BinaryTreeNode answer = null;
        while(!queue.isEmpty())
        {
            answer= queue.poll();
            if(answer.left!=null)
                queue.add(answer.left);
            if(answer.right!=null)
                queue.add(answer.right);
        }
        return answer;
    }
    
    public boolean isStructurallySame(BinaryTreeNode e)
    {
        if(e==null)
            return false;
        
        boolean b1 = e.getValue() == this.getValue();
        boolean b2 = this.getLeft() == null ? e.getLeft() == null : this.getLeft().isStructurallySame(e.getLeft());
        boolean b3 = this.getRight() == null ? e.getRight() == null : this.getRight().isStructurallySame(e.getRight());
        
        return b1 && b2 && b3;
    }
    
    public int getSumOfAllLeafValues()
    {
            int ansLeft = left==null ? 0 : left.getSumOfAllLeafValues();
            int ansRight = right == null ? 0 : right.getSumOfAllLeafValues();
            return this.value + ansLeft + ansRight;
    }
}
