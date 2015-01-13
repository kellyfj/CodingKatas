package com.kellyfj.codingkata.trees;

import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

public class TrieTest extends TestCase {
	private Trie t;
	
	@Override
	public void setUp() {
		t = new Trie();
		t.addWord("aspect");
		t.addWord("auspicious");
		t.addWord("autism");
		t.addWord("autocratic");
		t.addWord("automatic");
		t.addWord("automobile");
		t.addWord("Fred");
	}
	@Test
	public void test() {
		t.print();
	}
	
	@Test 
	public void testSearch_a() {
		List<String> res = t.search("a");
		
		assertNotNull(res);
		assertFalse(res.isEmpty());
		System.out.println("Found the following . . . . ");
		for(String s : res) {
			System.out.println("\t "+ s);
		}
		assertTrue(res.size() == 6);
		assertTrue(res.contains("aspect"));
		assertTrue(res.contains("auspicious"));
		assertTrue(res.contains("autism"));
		assertTrue(res.contains("autocratic"));
		assertTrue(res.contains("automatic"));
		assertTrue(res.contains("automobile"));
	}

	@Test 
	public void testSearch_au() {
		List<String> res = t.search("au");
		
		assertNotNull(res);
		assertFalse(res.isEmpty());
		assertTrue(res.size() == 5);
		assertTrue(res.contains("auspicious"));
		assertTrue(res.contains("autism"));
		assertTrue(res.contains("autocratic"));
		assertTrue(res.contains("automatic"));
		assertTrue(res.contains("automobile"));
	}
	
	@Test 
	public void testSearch_aut() {
		List<String> res = t.search("aut");
		
		assertNotNull(res);
		assertFalse(res.isEmpty());
		assertTrue(res.size() == 4);
		assertTrue(res.contains("autism"));
		assertTrue(res.contains("autocratic"));
		assertTrue(res.contains("automatic"));
		assertTrue(res.contains("automobile"));
	}

	@Test 
	public void testSearch_auto() {
		List<String> res = t.search("auto");
		
		assertNotNull(res);
		assertFalse(res.isEmpty());
		assertTrue(res.size() == 3);
		assertTrue(res.contains("autocratic"));
		assertTrue(res.contains("automatic"));
		assertTrue(res.contains("automobile"));
	}
	
	@Test 
	public void testSearch_automobile() {
		List<String> res = t.search("automobile");
		
		assertNotNull(res);
		assertFalse(res.isEmpty());
		assertTrue(res.size() == 1);
		assertTrue(res.contains("automobile"));
	}
	
	@Test 
	public void testSearch_automobile_nothing_found() {
		List<String> res = t.search("automobiles");
		
		assertNotNull(res);
		assertTrue(res.isEmpty());
	}
}
