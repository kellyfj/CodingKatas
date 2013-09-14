package com.kellyfj.codingkata.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAsAdjacencyMatrix {

       private final boolean adjacencyMatrix[][];
       private final List<Node> nodes;
       private final Map<Node,Integer> nodeToIndexMap = new HashMap<Node,Integer>();
       
       public GraphAsAdjacencyMatrix(List<Node> nodes)
       {
           adjacencyMatrix = new boolean[nodes.size()][nodes.size()];
           this.nodes=nodes;
           
           for(int i=0; i< nodes.size(); i++)
           {
              nodeToIndexMap.put(nodes.get(i), new Integer(i));
           }
       }
       
       public void addEdge(Node n1, Node n2)
       {
           if(!nodeToIndexMap.containsKey(n1) && !nodeToIndexMap.containsKey(n2))
               throw new IllegalArgumentException("One or more nodes not part of original ctor");
           
           int index1 = nodeToIndexMap.get(n1);
           int index2 = nodeToIndexMap.get(n2);
           
           adjacencyMatrix[index1][index2] = true;          
           //This is a DIRECTED graph - if we wanted UNDIRECTED graph we would also do
           //adjacencyMatrix[index2][index1] = true;          
       }

       
       public boolean areConnected(Node n1, Node n2)
       {
           if(!nodeToIndexMap.containsKey(n1) && !nodeToIndexMap.containsKey(n2))
               throw new IllegalArgumentException("One or more nodes not part of original ctor");

           int index1 = nodeToIndexMap.get(n1);
           int index2 = nodeToIndexMap.get(n2);
           
           return adjacencyMatrix[index1][index2];
       }
       
       public void prettyPrint()
       {
           System.out.print("\t");
           for(int i=0; i<adjacencyMatrix.length; i++)
           {
                 System.out.print(nodes.get(i).getName()+"  ");
           }
           System.out.println();

           
           for(int i=0; i<adjacencyMatrix.length; i++)
           {
               
               for(int j=0; j<adjacencyMatrix.length; j++)
               {
                   if(j==0)
                       System.out.print(nodes.get(i).getName()+"\t");
                   
                   System.out.print(adjacencyMatrix[i][j] ? "1  " : "0  ");
                   
                   if(j==adjacencyMatrix.length-1)
                       System.out.println("");
               }
           }
       }
}
