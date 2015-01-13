package com.kellyfj.codingkata.trees;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	private char character;
	private Map<Character,TrieNode> children = new HashMap<Character,TrieNode>();
	
	public TrieNode(char ch) {
		this.character = ch;
	}
	
	public Character getCharacter() {
		return this.character;
	}
	
	public void addChild(Character c, TrieNode t) {
		children.put(c,t);
	}
	
	public TrieNode getChild(Character c) {
		return children.get(c);
	}
	
	public boolean hasChild(Character c) {
		return children.containsKey(c);
	}
	
	Collection<TrieNode> getChildren() {
		return children.values();
	}
	
	public void print() {
		System.out.println(this.getCharacter());
		
		for(TrieNode t : this.getChildren()) {
			t.print();
		}
	}

}