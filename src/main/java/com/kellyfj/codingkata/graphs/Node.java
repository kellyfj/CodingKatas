package com.kellyfj.codingkata.graphs;

/**
 * This Node class is a bit confused - doing double duty as a Node and Adjacency list
 * 
 * @author kellyfj
 */
public class Node implements Comparable<Node>{

    private final String name;
    
    public Node(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Node previous;
    
    @Override
    public String toString() { return name; }
    
    public int compareTo(Node other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}
