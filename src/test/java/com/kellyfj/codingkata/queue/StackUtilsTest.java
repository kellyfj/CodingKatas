package com.kellyfj.codingkata.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StackUtilsTest {

	@Test
	public void testRPNEval() {
		int result = StackUtils.eval("3,4,+, 2,*, 1,+");
		
		assertEquals(15, result);
	}
	
	@Test
	public void testBracketMatcher() {
		
		boolean b = StackUtils.isWellFormed("{}");
		assertTrue(b);
		b = StackUtils.isWellFormed("{}()[]");
		assertTrue(b);	
		b = StackUtils.isWellFormed("([]){()}");
		assertTrue(b);	

		b = StackUtils.isWellFormed("([])()}");
		assertFalse(b);	
		b = StackUtils.isWellFormed("([]){()");
		assertFalse(b);
		b = StackUtils.isWellFormed("([]){()}}");
		assertFalse(b);
	}
}
