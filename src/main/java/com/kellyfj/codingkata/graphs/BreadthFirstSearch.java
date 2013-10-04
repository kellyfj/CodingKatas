package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearch {
    private static final List<Node> traversalOrder = new ArrayList<Node>();
    private static final Queue<Node> tempQueue = new LinkedBlockingQueue<Node>();
    
    public static String traverse(Graph g)
    {   
        List<Node> nodeList = g.getNodeList();
        
        //Start with the first node 
        Node n = nodeList.get(0);
        BreadthFirstTraverse(g,n);
        
        //Print Traversal order
        String str =  getTraversalOrder();
        System.out.println(str);
        return str;
    }

    
    private static void BreadthFirstTraverse(Graph g, Node n) {

        tempQueue.add(n);
        while(!tempQueue.isEmpty())
        {
            Node nextNode = tempQueue.poll();
            traversalOrder.add(nextNode);
            //printTraversalOrder();
            List<Node> nodeList = g.getAdjacentNodes(nextNode);
            tempQueue.addAll(nodeList);
        }
    }
    
    private static String getTraversalOrder()
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<traversalOrder.size(); i++)
        {
            String name = traversalOrder.get(i).getName();
            sb.append(name+",");
        }
        return sb.toString();
    }
}
