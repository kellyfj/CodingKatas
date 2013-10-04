package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class BreadthFirstSearchTest extends TestCase {

    private final char alphabet[] = new char[26];
    private List<Node> nodeList;
    
    public BreadthFirstSearchTest(String name) {
        super(name);
        for(char i='A'; i<='Z';i++) {
            alphabet[i-'A'] = i;
        }
    }

    @Override
    protected void setUp() throws Exception {
       nodeList = createNodeList(11);
    }

/*    public void testBreadthFirstTraversalNoEdges() {
      Graph g = new GraphAsAdjacencyMatrix(nodeList);
      String s= BreadthFirstSearch.traverse(g);
      System.out.println(s);
    }
*/
    
    public void testBreadthFirstTraversal()
    {
        Graph g = new GraphAsAdjacencyMatrix(nodeList);
        //                  0 (A)
        //              1(B)             2 (C)
        //        3(D)  4(E)   5 (F)          6(G)
        //       7(H)   8(I)     9 (J)        10(K)           
        g.addEdge(nodeList.get(0), nodeList.get(1));
        g.addEdge(nodeList.get(0), nodeList.get(2));
        
        g.addEdge(nodeList.get(1), nodeList.get(3));
        g.addEdge(nodeList.get(1), nodeList.get(4));
        g.addEdge(nodeList.get(1), nodeList.get(5));
        
        g.addEdge(nodeList.get(2), nodeList.get(6));
        
        g.addEdge(nodeList.get(3), nodeList.get(7));
        g.addEdge(nodeList.get(4), nodeList.get(8));
        g.addEdge(nodeList.get(5), nodeList.get(9));
        g.addEdge(nodeList.get(6), nodeList.get(10));
        
        
        String s= BreadthFirstSearch.traverse(g);
        
        assertEquals(s,"A,B,C,D,E,F,G,H,I,J,K,");
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
