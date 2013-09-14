package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch {
    //private static final Set<Node> visitedNode = new HashSet<Node>();
    private static final List<Node> traversalOrder = new ArrayList<Node>();
    private static final List<Node> tempQueue = new ArrayList<Node>();
    
    public static String traverse(Graph g)
    {
        
        List<Node> nodeList = g.getNodeList();
        
        //Start with the first node 
        Node n = nodeList.get(0);
        BreadthFirstTraverse(g,n);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<traversalOrder.size(); i++)
        {
            String name = traversalOrder.get(i).getName();
            sb.append(name+",");
        }
        return sb.toString();
    }

    
    private static void BreadthFirstTraverse(Graph g, Node n) {

        tempQueue.add(n);
        while(!tempQueue.isEmpty())
        {
            Node nextNode = tempQueue.remove(0);
            traversalOrder.add(nextNode);
            printTraversalOrder();
            List<Node> nodeList = g.getAdjacentNodes(nextNode);
            tempQueue.addAll(nodeList);
        }
    }
    
    private static void printTraversalOrder()
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<traversalOrder.size(); i++)
        {
            String name = traversalOrder.get(i).getName();
            sb.append(name+",");
        }
        System.out.println(sb.toString());
    }
}
