package com.kellyfj.codingkata.trees;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	private TrieNode root  = new TrieNode(' ');
	
	public void addWord(String s) {	
		TrieNode curr = root;
		for(Character ch : s.toCharArray()) {
			if( !curr.hasChild(ch) ) {
				TrieNode t = new TrieNode(ch);
				curr.addChild(ch, t);
				curr = t;
			} else {
				curr = curr.getChild(ch);
			}	
		}
	}

	public List<String> search(String prefix) {	
		if(prefix == null || prefix.isEmpty())
			return null;
		
		TrieNode curr = root;
		List<String> answer = new ArrayList<String>();
		for(Character ch : prefix.toCharArray()) {
			if( !curr.hasChild(ch) ) {
				return answer;
			} 
			curr = curr.getChild(ch);
		}
		//Else we have match(es) below prefix in the trie
	
		return getAllWords(curr, prefix);
	}
	
	private List<String> getAllWords(TrieNode start, final String prefix) {
		List<String> list = new ArrayList<String>();
		if (start.getChildren().isEmpty()) {
			list.add(prefix);
			return list;
		} else {
			for (TrieNode n : start.getChildren()) {
				list.addAll(getAllWords(n, prefix + n.getCharacter()));
			}
			return list;
		}

	}
	
	public void print() {
		root.print();
	}
}
