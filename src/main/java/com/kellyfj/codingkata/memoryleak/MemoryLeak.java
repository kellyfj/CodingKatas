package com.kellyfj.codingkata.memoryleak;

import java.util.HashSet;
import java.util.Set;

public class MemoryLeak {

	public static void main(String[] args) {
		Set<MyObject> s = new HashSet<MyObject>();
		final MyObject m = new MyObject();
		long i = 0;
		while (true) {			
			i++;

			if(!s.contains(m)) {
				s.add(m);
			}
			if (i % 1000 == 0) {
				System.out.println("Size : " + s.size());
			}
		}
	}
}
