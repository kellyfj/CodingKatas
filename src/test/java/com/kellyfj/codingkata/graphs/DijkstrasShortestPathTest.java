package com.kellyfj.codingkata.graphs;

import java.util.List;

import junit.framework.TestCase;


public class DijkstrasShortestPathTest extends TestCase {

    public void test() {
        Node v0 = new Node("Redvile");
        Node v1 = new Node("Blueville");
        Node v2 = new Node("Greenville");
        Node v3 = new Node("Orangeville");
        Node v4 = new Node("Purpleville");

        v0.adjacencies = new Edge[] { new Edge(v1, 5), new Edge(v2, 10), new Edge(v3, 8) };
        v1.adjacencies = new Edge[] { new Edge(v0, 5), new Edge(v2, 3), new Edge(v4, 7) };
        v2.adjacencies = new Edge[] { new Edge(v0, 10), new Edge(v1, 3) };
        v3.adjacencies = new Edge[] { new Edge(v0, 8), new Edge(v4, 2) };
        v4.adjacencies = new Edge[] { new Edge(v1, 7), new Edge(v3, 2) };
        Node[] vertices = { v0, v1, v2, v3, v4 };
        DijkstrasShortestPath.computePaths(v0);
        for (Node v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            List<Node> path = DijkstrasShortestPath.getShortestPathTo(v);
            System.out.println("Path: " + path);
        }
    }

}
