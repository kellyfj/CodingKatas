package com.kellyfj.codingkata.queue;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class StackWithCachedMaxTest {

	private static final int STACK_SIZE = 100;

	@Test
	public void testStackWithCachedMax() {

		Random r = new Random(System.currentTimeMillis());
		StackWithMax.Stack s = new StackWithMax.Stack();

		for (int i = 0; i < STACK_SIZE; i++) {
			Integer rand = r.nextInt(STACK_SIZE);
			s.push(rand);
		}

		Integer max = s.max();
		System.out.println("max: " + max);
		assertTrue(max < STACK_SIZE);

		for (int i = 0; i < STACK_SIZE - 1; i++) {
			s.pop();
			System.out.println("Max is now " + s.max());
		}
		Integer finalMax = s.max();
		assertTrue(finalMax < max);
	}
	
	@Test
	public void testStackWithMaxImproved() {

		Random r = new Random(System.currentTimeMillis());
		StackWithMax.ImprovedStack s = new StackWithMax.ImprovedStack();

		for (int i = 0; i < STACK_SIZE; i++) {
			Integer rand = r.nextInt(STACK_SIZE);
			s.push(rand);
		}

		Integer max = s.max();
		System.out.println("max: " + max);
		assertTrue(max < STACK_SIZE);

		for (int i = 0; i < STACK_SIZE - 1; i++) {
			s.pop();
			System.out.println("Max is now " + s.max());
		}
		Integer finalMax = s.max();
		assertTrue(finalMax < max);
	}
	
}
