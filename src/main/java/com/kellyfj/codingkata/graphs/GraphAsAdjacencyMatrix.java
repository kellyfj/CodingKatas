package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAsAdjacencyMatrix implements Graph {

    private final boolean adjacencyMatrix[][];
    private final List<Node> nodes;
    private final Map<Node, Integer> nodeToIndexMap = new HashMap<Node, Integer>();

    public GraphAsAdjacencyMatrix(List<Node> nodes) {
        adjacencyMatrix = new boolean[nodes.size()][nodes.size()];
        this.nodes = nodes;

        for (int i = 0; i < nodes.size(); i++) {
            nodeToIndexMap.put(nodes.get(i), new Integer(i));
        }
    }
    
    /* (non-Javadoc)
     * @see com.kellyfj.codingkata.graphs.Graph#getAdjacentNodes(com.kellyfj.codingkata.graphs.Node)
     */
    public List<Node> getAdjacentNodes(Node n1)
    {
        validateNode(n1);
        
        List<Node> retVal = new ArrayList<Node>();
        int i = nodeToIndexMap.get(n1);
		for (int j = 0; j < adjacencyMatrix.length; j++) {
			if (adjacencyMatrix[i][j]) {
				retVal.add(nodes.get(j));
			}
		}
        return retVal;
    }

    /* (non-Javadoc)
     * @see com.kellyfj.codingkata.graphs.Graph#addEdge(com.kellyfj.codingkata.graphs.Node, com.kellyfj.codingkata.graphs.Node)
     */
    public void addEdge(Node n1, Node n2) {
        validateNode(n1);
        validateNode(n2);

        int index1 = nodeToIndexMap.get(n1);
        int index2 = nodeToIndexMap.get(n2);

        adjacencyMatrix[index1][index2] = true;
        // This is a DIRECTED graph - if we wanted UNDIRECTED graph we would
        // also do
        // adjacencyMatrix[index2][index1] = true;
    }

    private void validateNode(Node n1) {
        if (!isNodeValid(n1))
            throw new IllegalArgumentException("Node ["+n1.getName()+"] was not part of original ctor");
    }

    private boolean isNodeValid(Node n) {
        return nodeToIndexMap.containsKey(n);
    }

    /* (non-Javadoc)
     * @see com.kellyfj.codingkata.graphs.Graph#areConnected(com.kellyfj.codingkata.graphs.Node, com.kellyfj.codingkata.graphs.Node)
     */
    public boolean areConnected(Node n1, Node n2) {
        validateNode(n1);
        validateNode(n2);

        int index1 = nodeToIndexMap.get(n1);
        int index2 = nodeToIndexMap.get(n2);

        return adjacencyMatrix[index1][index2];
    }

    /**
     * Pretty prints the matrix (somewhat!)
     */
    public void prettyPrint() {
        System.out.print("\t");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.print(nodes.get(i).getName() + "  ");
        }
        System.out.println();

        for (int i = 0; i < adjacencyMatrix.length; i++) {

            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (j == 0)
                    System.out.print(nodes.get(i).getName() + "\t");

                System.out.print(adjacencyMatrix[i][j] ? "1  " : "0  ");

                if (j == adjacencyMatrix.length - 1)
                    System.out.println("");
            }
        }
    }

    public List<Node> getNodeList() {
        return nodes;
    }
}
