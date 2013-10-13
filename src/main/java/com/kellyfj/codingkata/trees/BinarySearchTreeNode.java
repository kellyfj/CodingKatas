package com.kellyfj.codingkata.trees;

import java.util.Arrays;

public class BinarySearchTreeNode {
    private int value;
    protected BinarySearchTreeNode left;
    protected BinarySearchTreeNode right;

    private static BinarySearchTreeNode sortedArrayToBST(int arr[], int start, int end) {
        if (start > end) return null;
        
        // same as (start+end)/2, avoids overflow.
        int mid = start + (end - start) / 2;
        BinarySearchTreeNode node = new BinarySearchTreeNode(arr[mid]);
        node.setLeft(sortedArrayToBST(arr, start, mid-1));
        node.setRight(sortedArrayToBST(arr, mid+1, end));
        return node;
      }
       
    public static BinarySearchTreeNode fromArray(int[] arr) {
        Arrays.sort(arr);
        return sortedArrayToBST(arr, 0, arr.length-1);
      }
      
    public BinarySearchTreeNode(int value) {
        this.value = value;
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

    public BinarySearchTreeNode search(int data) {
        if (data < this.getValue() && left != null)
            return this.getLeft().search(data);
        if (data > this.getValue() && right != null)
            return this.getRight().search(data);
        if (value == data)
            return this;
        else
            return null;
    }

    public BinarySearchTreeNode getKthSmallestElement(int k) {
        BinarySearchTreeNode node = this;
        int count = k;
        int sizeOfLeftSubtree = 0;
        while (node != null) {
            if (node.getLeft() != null)
                sizeOfLeftSubtree = node.getLeft().getSize();
            else
                sizeOfLeftSubtree = 0;

            if (sizeOfLeftSubtree + 1 == count)
                return node;
            else if (sizeOfLeftSubtree < count) {
                node = node.getRight();
                count = count - (sizeOfLeftSubtree + 1);
            } else {
                node = node.getLeft();
            }
        }
        return null;
    }

    public BinarySearchTreeNode getKthLargestElement(int k) {
        BinarySearchTreeNode node = this;
        int count = k;
        int sizeOfRightSubtree = 0;
        while (node != null) {
            if (node.getRight() != null)
                sizeOfRightSubtree = node.getRight().getSize();
            else
                sizeOfRightSubtree = 0;

            if (sizeOfRightSubtree + 1 == count)
                return node;
            else if (sizeOfRightSubtree < count) {
                node = node.getLeft();
                count -= sizeOfRightSubtree + 1;
            } else {
                node = node.getRight();
            }
        }
        return null;
    }
    
    public int getSize() {
        int leftSize = left == null ? 0 : left.getSize();
        int rightSize = right == null ? 0 : right.getSize();
        return 1 + leftSize + rightSize;
    }
}
