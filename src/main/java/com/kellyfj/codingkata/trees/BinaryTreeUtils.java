package com.kellyfj.codingkata.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A Set of Utilities for Binary Trees
 * @author kellyfj
 */
public class BinaryTreeUtils {

    public static String bfsTraverse(BinaryTreeNode n) {
        final Queue<BinaryTreeNode> tempQueue = new LinkedBlockingQueue<BinaryTreeNode>();
        final BinaryTreeNode DUMMY = new BinaryTreeNode(Integer.MAX_VALUE);
        StringBuilder sb = new StringBuilder();
        tempQueue.add(n);
        tempQueue.add(DUMMY);

        while (!tempQueue.isEmpty()) {
            BinaryTreeNode nextNode = tempQueue.poll();
            if (nextNode.equals(DUMMY)) {
                sb.append("\n");
                if (!tempQueue.isEmpty()) {
                    tempQueue.add(DUMMY);
                }
            } else {
                sb.append(nextNode.getValue() + ", ");
                BinaryTreeNode left = nextNode.getLeft();
                BinaryTreeNode right = nextNode.getRight();

                if (left != null)
                    tempQueue.add(left);
                if (right != null)
                    tempQueue.add(right);
            }
        }
        
        return sb.toString();
    }
    
    public static boolean dfsTraverse(BinaryTreeNode head, int k) {

        if (head == null)
            return false;

        System.out.println("Visiting node (" + head.getValue() + ") comparing to " + k);

        if (head.getValue() == k)
            return true;

        boolean left = dfsTraverse(head.getLeft(), k);
        boolean right = dfsTraverse(head.getRight(), k);

        return left || right;
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
     * BFS using an iterative solution
     * TIME COMPLEXITY: O(n) since considering each node twice on-queue, off-queue
     * SPACE COMPLEXITY: O(n/2) width of tree at bottom on the queue = O(n)
     */
    public static List<BinaryTreeNode> findShortestPathBFS(BinaryTreeNode head) {
        if(head==null) return null;
        
        Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
        q.add(head);
        BinaryTreeNode b=null;
        //Find first leaf
        while(!q.isEmpty()) {
             b = q.poll();
            if(isLeaf(b)) break;
            if(b.getLeft() !=null) q.add(b.getLeft());
            if(b.getRight() !=null) q.add(b.getRight());
        }
        
        //Now go back up tree constructing path
        List<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
        while(b!=head) {
            list.add(b);
            b = b.getParent();
        }
        list.add(head);
        
        return list;
    }
    
    private static boolean isLeaf(BinaryTreeNode b) {
        return b.getLeft()==null && b.getRight()==null;
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
        if (root == null) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue() + ",");
        sb.append(preOrder(root.getLeft()));
        sb.append(preOrder(root.getRight()));
        return sb.toString();
    }

    /**
     * In order traversal of a binary tree.
     * Useful for things like validating a BST
     * @param n the head binary tree node
     * @return String containing the traversal order
     */
    public static String inOrder(BinaryTreeNode n) {
        if (n == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(inOrder(n.getLeft()));
        sb.append(n.getValue() + ",");
        sb.append(inOrder(n.getRight()));
        return sb.toString();
    }
    
    /**
     * Post Order Traversal of a Binary Tree
     * @param n the head binary tree node
     * @return String containing the traversal order
     */
    public static String postOrder(BinaryTreeNode n) {
        if (n == null) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append(postOrder(n.getLeft()));
        sb.append(postOrder(n.getRight()));
        sb.append(n.getValue() + ",");
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
     * Find the Lowest Common Ancestor of two nodes in a Binary Tree.
     * 
     * Recurse down the tree until you find a,b Then return a non-null and start
     * unwinding the stack upwards until both left and right branches are not
     * null and that node is your answer
     * 
     * Time Complexity: O(n) where n is the number of nodes (source: EPI pp. 257)
     * Space Complexity: ??? O(log n) or O(h) where h is the height of the tree??
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
    
    public static BinaryTreeNode lowestCommonAncestor_Order1Space_OrderHTime(BinaryTreeNode head, 
            BinaryTreeNode a, BinaryTreeNode b) {
        if (head == null)
            throw new IllegalArgumentException("head cannot be null");
        if (a == null)
            throw new IllegalArgumentException("a cannot be null");
        if (b == null)
            throw new IllegalArgumentException("b cannot be null");

        // Get depth from root of each node
        int depthDiff = a.getDepth() - b.getDepth();

        // Bring deepest pointer to same level as other pointer
        BinaryTreeNode deepestNode = null;
        BinaryTreeNode otherNode = null;
        if (depthDiff < 0) { // b is deeper than a
            deepestNode = b;
            otherNode = a;

            while (depthDiff++ < 0) {
                deepestNode = deepestNode.getParent();
            }
        } else { // a is deeper than b
            deepestNode = a;
            otherNode = b;
            while (depthDiff-- > 0) {
                deepestNode = deepestNode.getParent();
            }
        }

        // Now advance both pointers until they find a common ancestor
        while (deepestNode != otherNode) {
            deepestNode = deepestNode.getParent();
            otherNode = otherNode.getParent();
        }

        return deepestNode;
    }

    
    public static boolean isSymmetric_aka_isMirror(BinaryTreeNode root) {	
    	return symmetric_aka_mirror(root.getLeft(), root.getRight());
    }
    
	private static boolean symmetric_aka_mirror(BinaryTreeNode left,
			BinaryTreeNode right) {
		
		if (left == null || right == null)
			return left == null && right == null;
		
		return left.getValue() == right.getValue()
				&& symmetric_aka_mirror(left.getLeft(), right.right)
				&& symmetric_aka_mirror(left.getRight(), right.getLeft());
	}
}

