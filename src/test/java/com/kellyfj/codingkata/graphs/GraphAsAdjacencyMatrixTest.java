package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class GraphAsAdjacencyMatrixTest extends TestCase {

    private final char alphabet[] = new char[26];
    private List<Node> nodeList;
    
    public GraphAsAdjacencyMatrixTest(String name) {
        super(name);
        for(char i='A'; i<='Z';i++) {
            alphabet[i-'A'] = i;
        }
    }

    @Override
    protected void setUp() throws Exception {
       nodeList = createNodeList(10);
    }

    public void testGraphAsAdjacencyMatrix() {
       GraphAsAdjacencyMatrix g = new GraphAsAdjacencyMatrix(nodeList);
       g.prettyPrint();
    }

    public void testAddEdge() {
        GraphAsAdjacencyMatrix g = new GraphAsAdjacencyMatrix(nodeList);
        for(int i=0; i<nodeList.size(); i++)
        {
            for(int j=0; j<nodeList.size(); j++)
            {
                if(i%2==j%2){
                    g.addEdge(nodeList.get(i), nodeList.get(j));
                    assertTrue(g.areConnected(nodeList.get(i), nodeList.get(j)));
                }
                else {
                    assertFalse(g.areConnected(nodeList.get(i), nodeList.get(j)));
                }
            }
        }
        g.prettyPrint();
    }

    private List<Node> createNodeList(int max) {

        List<Node> returnVal = new ArrayList<Node>(max);
        for(int i=0; i< max; i++)
        {   
            String name = ""+alphabet[i];
            Node n = new Node(name);
            returnVal.add(n);
        }
        return returnVal;
    }
}
