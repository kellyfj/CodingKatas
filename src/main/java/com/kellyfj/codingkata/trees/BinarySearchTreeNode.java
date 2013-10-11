package com.kellyfj.codingkata.trees;

public class BinarySearchTreeNode {
    private int value;
    protected BinarySearchTreeNode left;
    protected BinarySearchTreeNode right;

    public BinarySearchTreeNode(int value) {
        this.value= value;
     }
    
    public int getValue() {
        return value;
    }



    public void setValue(int value) {
        this.value = value;
    }



    public BinarySearchTreeNode getLeft() {
        return left;
    }



    public void setLeft(BinarySearchTreeNode left) {
        this.left = left;
    }



    public BinarySearchTreeNode getRight() {
        return right;
    }



    public void setRight(BinarySearchTreeNode right) {
        this.right = right;
    }
  
    public BinarySearchTreeNode search(int data)
    {
        if(data < this.getValue()  && left!=null)
            return this.getLeft().search(data);
        if(data > this.getValue() && right!=null)
            return this.getRight().search(data);
        if(value == data)
            return this;
        else
            return null;
    }

}
