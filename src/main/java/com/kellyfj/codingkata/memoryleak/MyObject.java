package com.kellyfj.codingkata.memoryleak;

public class MyObject {
	
	@Override
	public int hashCode() {
		
		return (int) System.nanoTime();
	}
	/*
	@Override
	public boolean equals(Object o) {
		
		return false;
	}*/
}
