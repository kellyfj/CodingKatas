package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearch {
    private static final Node DUMMY = new Node("\n");

    public static String traverse(Graph g) {
        List<Node> nodeList = g.getNodeList();

        // Start with the first node
        Node n = nodeList.get(0);
        String ret = BreadthFirstTraverse(g, n);

        return ret;
    }

    private static String BreadthFirstTraverse(Graph g, Node n) {
    	Queue<Node> tempQueue = new LinkedBlockingQueue<Node>();
    	List<Node> traversalOrder = new ArrayList<Node>();
    	 
        if (tempQueue.isEmpty()) {
            tempQueue.add(n);
            tempQueue.add(DUMMY);
            traversalOrder.add(DUMMY);
        } else {
            tempQueue.add(n);
        }

        while (!tempQueue.isEmpty()) {
            Node nextNode = tempQueue.poll();
            if (nextNode.equals(DUMMY)) {
                if (!tempQueue.isEmpty()) {
                    tempQueue.add(DUMMY);
                    traversalOrder.add(DUMMY);
                }
            } else {
                traversalOrder.add(nextNode);
                printTraversalOrder(traversalOrder);
                List<Node> nodeList = g.getAdjacentNodes(nextNode);
                tempQueue.addAll(nodeList);
            }
        }
        
        String str = getPrintableTraversalOrder(traversalOrder);
        System.out.println(str);
        return str;
    }

    private static void printTraversalOrder(List<Node> order) {
        System.out.println(getPrintableTraversalOrder(order));
    }
    
    private static String getPrintableTraversalOrder(List<Node> order) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.size(); i++) {
            String name = order.get(i).getName();
            sb.append(name + " ");
        }
        return sb.toString();
    }
}
