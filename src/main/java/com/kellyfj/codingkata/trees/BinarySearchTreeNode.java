package com.kellyfj.codingkata.trees;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Binary Search Tree ADT
 * @author kellyfj
 */
public class BinarySearchTreeNode extends BinaryTreeNode {

    public BinarySearchTreeNode(int value) {
        super(value);
    }
    
    /**
     * Convert an Array of Integers into a Binary Search Tree
     * @param arr
     * @return
     */
    public static BinarySearchTreeNode fromArray(int[] arr) {
        Arrays.sort(arr);
        return sortedArrayToBST(arr, 0, arr.length - 1);
    }

    private static BinarySearchTreeNode sortedArrayToBST(int arr[], int start, int end) {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;
        BinarySearchTreeNode node = new BinarySearchTreeNode(arr[mid]);
        node.setLeft(sortedArrayToBST(arr, start, mid - 1));
        node.setRight(sortedArrayToBST(arr, mid + 1, end));
        return node;
    }

    /**
     * Find a node with a particular value in the Tree
     * @param data
     * @return
     */
    public BinarySearchTreeNode search(int data) {
        if (data < this.getValue() && left != null)
            return ((BinarySearchTreeNode) this.getLeft()).search(data);
        if (data > this.getValue() && right != null)
            return ((BinarySearchTreeNode) this.getRight()).search(data);
        if (this.getValue() == data)
            return this;
        else
            return null;
    }

    public BinaryTreeNode getKthSmallestElement(int k) {
        BinaryTreeNode node = this;
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

    public BinaryTreeNode getKthLargestElement(int k) {
        BinaryTreeNode node = this;
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
                count = count - (sizeOfRightSubtree + 1);
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        int leftSize = left == null ? 0 : left.getSize();
        int rightSize = right == null ? 0 : right.getSize();
        return 1 + leftSize + rightSize;
    }

    @Override
    public boolean equals(Object a) {
        if (a == null)
            return false;
        if (!(a instanceof BinaryTreeNode))
            return false;
        BinaryTreeNode b = (BinaryTreeNode) a;
        return b.getValue() == this.getValue();
    }

    @Override
    public String toString() {
        LinkedBlockingQueue<BinaryTreeNode> q = new LinkedBlockingQueue<BinaryTreeNode>();
        final BinaryTreeNode DUMMY = new BinaryTreeNode(Integer.MAX_VALUE);

        q.add(this);
        q.add(DUMMY);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            BinaryTreeNode node = q.poll();

            if (node.equals(DUMMY)) {
                if (!q.isEmpty()) {
                    q.add(DUMMY);
                }
                sb.append("\n");
            } else {
                sb.append(node.getValue() + " ");
                if (node.getLeft() != null)
                    q.add(node.getLeft());
                if (node.getRight() != null)
                    q.add(node.getRight());
            }

        }

        return sb.toString();
    }
    
}
