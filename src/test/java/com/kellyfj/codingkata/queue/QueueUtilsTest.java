package com.kellyfj.codingkata.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.kellyfj.codingkata.trees.BinaryTreeNode;

public class QueueUtilsTest {

	@Test
	public void testDepthOrder() {
		BinaryTreeNode tree = new BinaryTreeNode(5);
		List<List<Integer>> listOfLists = QueueUtils.binaryTreeDepthOrder(tree);
		
		assertNotNull(listOfLists);
		assertEquals(1, listOfLists.size());
		assertEquals(1, (listOfLists.get(0)).size());
		
		printListOfLists(listOfLists);
	}
	
	@Test 
	public void testBalancedTree() {
		BinaryTreeNode tree = new BinaryTreeNode(5);
		BinaryTreeNode left = new BinaryTreeNode(3);
		left.setLeft(new BinaryTreeNode(1));
		left.setRight(new BinaryTreeNode(2));
		tree.setLeft(left);
		
		BinaryTreeNode right = new BinaryTreeNode(7);
		right.setLeft(new BinaryTreeNode(6));
		right.setRight(new BinaryTreeNode(8));
		tree.setRight(right);
		List<List<Integer>> listOfLists = QueueUtils.binaryTreeDepthOrder(tree);

		assertNotNull(listOfLists);
		assertEquals(3, listOfLists.size()); //5
		assertEquals(2, (listOfLists.get(1)).size()); //3 and 7
		assertEquals(4, (listOfLists.get(2)).size()); // 1 2 6 8 
		printListOfLists(listOfLists);
	}
	
	private void printListOfLists(List<List<Integer>>  listsOfLists) {
		System.out.println(listsOfLists);
	}
}
