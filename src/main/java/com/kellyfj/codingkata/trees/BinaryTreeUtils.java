package com.kellyfj.codingkata.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A Set of Utilities for Binary Trees
 * @author kellyfj
 */
public class BinaryTreeUtils {

    public static void bfsTraverse(BinarySearchTreeNode n) {
        final Queue<BinaryTreeNode> tempQueue = new LinkedBlockingQueue<BinaryTreeNode>();
        final BinaryTreeNode DUMMY = new BinaryTreeNode(Integer.MAX_VALUE);

        tempQueue.add(n);
        tempQueue.add(DUMMY);

        while (!tempQueue.isEmpty()) {
            BinaryTreeNode nextNode = tempQueue.poll();
            if (nextNode.equals(DUMMY)) {
                System.out.println("");
                if (!tempQueue.isEmpty()) {
                    tempQueue.add(DUMMY);
                }
            } else {
                System.out.print(nextNode.getValue() + ", ");
                BinaryTreeNode left = nextNode.getLeft();
                BinaryTreeNode right = nextNode.getRight();

                if (left != null)
                    tempQueue.add(left);
                if (right != null)
                    tempQueue.add(right);
            }
        }
    }
    
    /**
     * Find the Shortest Path through a binary tree
     * @param n
     * @return
     */
    public static List<Integer> findShortestPath(BinaryTreeNode n) {
        if (n == null) return null;
        BinaryTreeNode left = n.getLeft();
        BinaryTreeNode right = n.getRight();

        List<Integer> ret = new ArrayList<Integer>();
        if (left == null && right == null) {
            ret.add(n.getValue());
        } else {
            List<Integer> l = findShortestPath(n.getLeft());
            List<Integer> r = findShortestPath(n.getRight());
            ret.add(n.getValue());
            if(l!=null && r!=null) {
                if(l.size() < r.size())
                    ret.addAll(l);
                else
                    ret.addAll(r);
            }
        } 
        return ret;
    }
    
    /**
     * Find the Longest Path through a Binary Tree
     * @param n
     * @return
     */
    public static List<Integer> findLongestPath(BinaryTreeNode n) {
        if (n == null)
            return null;
        BinaryTreeNode left = n.getLeft();
        BinaryTreeNode right = n.getRight();

        List<Integer> ret = new ArrayList<Integer>();
        if (left == null && right == null) {
            ret.add(n.getValue());
        } else {
            List<Integer> l = findLongestPath(n.getLeft());
            List<Integer> r = findLongestPath(n.getRight());
            ret.add(n.getValue());
            if(l!=null && r!=null) {
                if(l.size() > r.size())
                    ret.addAll(l);
                else
                    ret.addAll(r);
            }
        } 
        return ret;
    }
    
    /**
     * PreOrder Traversal of a Binary Tree
     * @param root the head binary tree node
     * @return String containing the traversal order
     */
    public static String preOrder(BinaryTreeNode root) {

        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue() + ",");

        sb.append(preOrder(root.getLeft()));
        sb.append(preOrder(root.getRight()));

        return sb.toString();
    }

    /**
     * In order traversal of a binary tree
     * @param n the head binary tree node
     * @return String containing the traversal order
     */
    public static String inOrder(BinaryTreeNode n) {
        if (n == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(inOrder(n.getLeft()));
        sb.append(n.getValue() + ",");
        sb.append(inOrder(n.getRight()));

        return sb.toString();
    }
    
    public static String inOrder_Order1Space(BinaryTreeNode n) {
        if (n == null)
            return "";

        BinaryTreeNode prev = null;
        BinaryTreeNode curr = n;
        BinaryTreeNode next = null;
        StringBuilder sb = new StringBuilder();

        while (curr != null) {
            if (prev == null || (prev.getLeft() != null && prev.getLeft().equals(curr))
                             || (prev.getRight() != null && prev.getRight().equals(curr))) {
                
                //Keep going down left first
                if (curr.getLeft() != null) {
                    next = curr.getLeft();
                } else { //Go down right
                    sb.append(curr.getValue() + ",");
                    next = (curr.getRight() != null ? curr.getRight() : curr.getParent());
                }
            } else if (curr.getLeft()!=null && curr.getLeft().equals(prev)) { 
                //Visited the left child already
                sb.append(curr.getValue() + ",");
                next = (curr.getRight() != null ? curr.getRight() : curr.getParent());
            } else { // We visited the right child already curr.getRight() == prev
                next = curr.getParent();
            }

            prev = curr;
            curr = next;
        }

        return sb.toString();
    }

    /**
     * Post Order Traversal of a Binary Tree
     * @param n the head binary tree node
     * @return String containing the traversal order
     */
    public static String postOrder(BinaryTreeNode n) {
        if (n == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(postOrder(n.getLeft()));
        sb.append(postOrder(n.getRight()));
        sb.append(n.getValue() + ",");
        return sb.toString();
    }

    /**
     * Find the Lowest Common Ancestor of two nodes in a Binary Tree.
     * 
     * Recurse down the tree until you find a,b Then return a non-null and start
     * unwinding the stack upwards until both left and right branches are not
     * null and that node is your answer
     * 
     * @param subTreeHead
     * @param a
     * @param b
     * @return
     */
    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode head, BinaryTreeNode a,
            BinaryTreeNode b) {

        if (head == null)
            return null;

        if (head.equals(a) || head.equals(b))
            return head;
        BinaryTreeNode l = lowestCommonAncestor(head.getLeft(), a, b);
        BinaryTreeNode r = lowestCommonAncestor(head.getRight(), a, b);

        if (l != null && r != null)
            return head; // if a and b are on both sides of the subtree
        return l != null ? l : r; // either one of a,b is on one side OR a,b is
                                  // not in L&R subtrees
    }
}

