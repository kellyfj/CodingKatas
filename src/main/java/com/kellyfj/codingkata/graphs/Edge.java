package com.kellyfj.codingkata.graphs;

/**
 * Edge Implementation
 * 
 * @author kellyfj
 */
class Edge {
    public final Node target;
    public final double weight;

    public Edge(Node argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}