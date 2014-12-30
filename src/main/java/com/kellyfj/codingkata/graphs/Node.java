package com.kellyfj.codingkata.graphs;

/**
 * This Node class is a bit confused - doing double duty as a Node and Adjacency list
 * 
 * @author kellyfj
 */
public class Node implements Comparable<Node>{

    private final String name;
    
	public Node(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
    
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Node previous;
    
	@Override
	public String toString() {
		return name;
	}
    
	@Override
	public int compareTo(Node other) {
		return Double.compare(minDistance, other.minDistance);
	}
    
    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(! (o instanceof Node)) return false;
        Node n = (Node) o;
        return n.getName().equals(this.getName());
    }
}
