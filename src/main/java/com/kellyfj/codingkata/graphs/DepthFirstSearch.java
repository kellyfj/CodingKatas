package com.kellyfj.codingkata.graphs;

import java.util.List;

public class DepthFirstSearch {
    
    public static String traverse(Graph g)
    {
        List<Node> nodeList = g.getNodeList();
        
        //Start with the first node 
        Node n = nodeList.get(0);
        return DepthFirstTraversal(g,n);
    }

    private static String DepthFirstTraversal(Graph g, Node n) {
       StringBuilder sb= new StringBuilder();
       sb.append(n.getName()+",");
           
        List<Node> nodeList = g.getAdjacentNodes(n);
                
        for(int i=0; i< nodeList.size(); i++)
        {
            sb.append(DepthFirstTraversal(g,nodeList.get(i)));
        }
        
        return sb.toString();
    }
}
