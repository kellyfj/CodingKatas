package com.kellyfj.codingkata.graphs;

import java.util.List;

public interface Graph {

    /**
     * Return a list of those nodes adjacent
     * @param n1
     * @return
     */
    public abstract List<Node> getAdjacentNodes(Node n1);

    /**
     * Add an Edge to the Graph
     * @param n1
     * @param n2
     */
    public abstract void addEdge(Node n1, Node n2);

    /**
     * Tells us if the nodes are connected or not
     * @param n1
     * @param n2
     * @return
     */
    public abstract boolean areConnected(Node n1, Node n2);

    /**
     * 
     * @return
     */
    public abstract List<Node> getNodeList();

}