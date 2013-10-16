package com.kellyfj.codingkata.trees;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTreeNode {
    private int value;
    protected BinarySearchTreeNode left;
    protected BinarySearchTreeNode right;

    /**
     * Recurse down the tree until you find a,b
     * Then return a non-null and start unwinding the stack upwards until both
     * left and right branches are not null and that node is your answer
     * 
     * @param subTreeHead
     * @param a
     * @param b
     * @return
     */
    public static BinarySearchTreeNode lowestCommonAncestor(BinarySearchTreeNode subTreeHead, 
            BinarySearchTreeNode a,
            BinarySearchTreeNode b) {

        if (subTreeHead == null)
            return null;

        if (subTreeHead.equals(a) || subTreeHead.equals(b))
            return subTreeHead;
        BinarySearchTreeNode l = lowestCommonAncestor(subTreeHead.getLeft(), a, b);
        BinarySearchTreeNode r = lowestCommonAncestor(subTreeHead.getRight(), a, b);

        if (l != null && r != null)
            return subTreeHead; // if a and b are on both sides of the subtree
        return l != null ? l : r; // either one of a,b is on one side OR a,b is
                                  // not in L&R subtrees

    }

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
    
    @Override
    public boolean equals(Object a) {
        if(a==null) return false;
        if(! (a instanceof BinarySearchTreeNode)) return false;
        BinarySearchTreeNode b = (BinarySearchTreeNode)a;
        return b.getValue() == this.getValue();
        
    }
    
    @Override
    public String toString() {
        LinkedBlockingQueue<BinarySearchTreeNode> q = new LinkedBlockingQueue<BinarySearchTreeNode>();
        final BinarySearchTreeNode DUMMY = new BinarySearchTreeNode(Integer.MAX_VALUE);
        
        q.add(this);
        q.add(DUMMY);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            BinarySearchTreeNode node = q.poll();
            
            if (node.equals(DUMMY)) {
                if (!q.isEmpty()) {
                    q.add(DUMMY);
                }
               sb.append("\n"); 
            } else {
                sb.append(node.getValue()+ " ");
                if(node.getLeft() != null) q.add(node.getLeft());
                if(node.getRight() != null) q.add(node.getRight());
            }
            
        }
        
        return sb.toString();
    }
}
