package com.kellyfj.codingkata.trees;

public class BinaryTreeNode {
    
    private int value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    
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
}
