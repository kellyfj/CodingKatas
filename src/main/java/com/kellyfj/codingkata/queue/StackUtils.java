package com.kellyfj.codingkata.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUtils {

	/**
	 * EPIJ 8.2: Write a program that takes an arithmetic expression in RPN and returns the
	 * number that the expression evaluates to
	 * 
	 * A String is said to be Reverse Polish Notation if 1. It is a single digit or
	 * sequence of digits prefixed with an option e.g. "42" or "-42" 2. It is of the
	 * form "A, B, op" where A and B are RPN expressions and "op" is one of + - * or
	 * / For example: "1729" or "3,4,+, 2, *, 1, +" or "-641, 6, /, 28, /"
	 */
	public static int eval(String rpn) {
		Deque<Integer> intermediateResults = new ArrayDeque<>();
		String delimiter = ",";
		String[] symbols = rpn.split(delimiter);

		for (String symbol : symbols) {
			String token = symbol.trim();
			if (token.length() == 1 && "+-*/".contains(token)) {
				int y = intermediateResults.removeFirst();
				int x = intermediateResults.removeFirst();
				switch (token.charAt(0)) {
				case '+':
					intermediateResults.addFirst(x + y);
					break;
				case '-':
					intermediateResults.addFirst(x - y);
					break;
				case '*':
					intermediateResults.addFirst(x * y);
					break;
				case '/':
					intermediateResults.addFirst(x / y);
					break;
				default:
					throw new IllegalArgumentException("Malformed RPN at " + token);
				}
			} else {
				// token is a number
				intermediateResults.addFirst(Integer.parseInt(token));
			}
		}
		return intermediateResults.removeFirst();
	}
	
	
	/**
	 * EPIJ 8.3: Test A String for well-formedness A String over the characters
	 * "{,}, (,), [,]" is said to be well-formed if the different types of brackets
	 * match in the right order. For example "([]){()}" is well formed as is
	 * "[()[]{()()}]" however "{)" is not nor is "[()[]{()()"
	 */
	public static boolean isWellFormed(String s) {
		Deque<Character> leftChars = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char token = s.charAt(i);
			if (token == '(' || token == '{' || token == '[') {
				leftChars.addFirst(token);
			} else {
				if (leftChars.isEmpty()) {
					return false;
				}
				if ((token == ')' && leftChars.peekFirst() != '(') || 
					(token == '}' && leftChars.peekFirst() != '{') || 
					(token == ']' && leftChars.peekFirst() != '[')) {
					return false;
				}
				leftChars.removeFirst();
			}
		}
		return leftChars.isEmpty();
	}
}
