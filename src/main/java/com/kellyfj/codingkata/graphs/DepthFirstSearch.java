package com.kellyfj.codingkata.graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirstSearch {
    private static final Set<Node> visitedNode = new HashSet<Node>();
    private static int maxNodes;
    
    
    public static String traverse(Graph g)
    {
        List<Node> nodeList = g.getNodeList();
        maxNodes = nodeList.size();
        
        Node n = nodeList.get(0);
        return DepthFirstTraversal(g,n);
        
    }

    private static String DepthFirstTraversal(Graph g, Node n) {
        StringBuilder sb= new StringBuilder();
       if(visitedNode.contains(n))
           return sb.toString();
       else
           sb.append(n.getName()+",");
           
        List<Node> nodeList = g.getAdjacentNodes(n);
        
        
        //Start from 1 not 0
        for(int i=0; i< nodeList.size(); i++)
        {
            sb.append(DepthFirstTraversal(g,nodeList.get(i)));
        }
        
        return sb.toString();
    }
}
