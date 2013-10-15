package com.kellyfj.codingkata.trees;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTreeUtils {

    public static void bfsTraverse(BinarySearchTreeNode n) {
        final Queue<BinarySearchTreeNode> tempQueue = new LinkedBlockingQueue<BinarySearchTreeNode>();
        final BinarySearchTreeNode DUMMY = new BinarySearchTreeNode(Integer.MAX_VALUE);

        tempQueue.add(n);
        tempQueue.add(DUMMY);

        while (!tempQueue.isEmpty()) {
            BinarySearchTreeNode nextNode = tempQueue.poll();
            if (nextNode.equals(DUMMY)) {
                System.out.println("");
                if (!tempQueue.isEmpty()) {
                    tempQueue.add(DUMMY);
                }
            } else {
                System.out.print(nextNode.getValue()+", ");
                BinarySearchTreeNode left = nextNode.getLeft();
                BinarySearchTreeNode right = nextNode.getRight();

                if (left != null)
                    tempQueue.add(left);
                if (right != null)
                    tempQueue.add(right);
            }
        }
    }
}
