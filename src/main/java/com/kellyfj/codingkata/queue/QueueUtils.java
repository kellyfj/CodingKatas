package com.kellyfj.codingkata.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.kellyfj.codingkata.trees.BinaryTreeNode;

public class QueueUtils {

	/**
	 * EPIJ 8.6: Compute Binary Tree nodes in order of increasing depth
	 * 
	 * Brute force - write nodes into an array while simultaneously computing depth
	 * then sort the array using node depth as the sort key. 
	 * Time Complexity: O(n) + O(n log n) = O(n log n) 
	 * Space Complexity: O(n)
	 * 
	 * This solution uses queues
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 */
	public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode tree) {
		List<List<Integer>> result = new ArrayList<>();
		if (tree == null) {
			return result;
		}

		Queue<BinaryTreeNode> currDepthNodes = new ArrayDeque<>();
		currDepthNodes.add(tree);
		while (!currDepthNodes.isEmpty()) {
			Queue<BinaryTreeNode> nextDepthNodes = new ArrayDeque<>();
			List<Integer> thisLevel = new ArrayList<>();
			while (!currDepthNodes.isEmpty()) {
				BinaryTreeNode curr = currDepthNodes.poll();
				if (curr != null) {
					thisLevel.add(curr.getValue());

					if (curr.getLeft() != null) {
						nextDepthNodes.add(curr.getLeft());
					}
					if (curr.getRight() != null) {
						nextDepthNodes.add(curr.getRight());
					}
				}
			}
			result.add(thisLevel);
			currDepthNodes = nextDepthNodes;
		}
		return result;
	}
	
}
