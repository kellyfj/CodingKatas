package com.kellyfj.codingkata.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

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
    
    public static List<Integer> findShortestPath(BinaryTreeNode n) {
        if (n == null)
            return null;

        //System.out.println("Visiting Node:" + n.getValue());
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
    public static List<Integer> findLongestPath(BinaryTreeNode n) {
        if (n == null)
            return null;

        //System.out.println("Visiting Node:" + n.getValue());
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
    
    public static String preOrder(BinaryTreeNode root) {

        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue() + ",");

        sb.append(preOrder(root.getLeft()));
        sb.append(preOrder(root.getRight()));

        return sb.toString();
    }

    public static String inOrder(BinaryTreeNode n) {
        if (n == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(inOrder(n.getLeft()));
        sb.append(n.getValue() + ",");
        sb.append(inOrder(n.getRight()));

        return sb.toString();
    }

    public static String postOrder(BinaryTreeNode n) {
        if (n == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(postOrder(n.getLeft()));
        sb.append(postOrder(n.getRight()));
        sb.append(n.getValue() + ",");
        return sb.toString();
    }

}
