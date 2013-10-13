package com.kellyfj.codingkata.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearch {
    private static final List<Node> traversalOrder = new ArrayList<Node>();
    private static final Queue<Node> tempQueue = new LinkedBlockingQueue<Node>();
    private static final Node DUMMY = new Node("\n");

    public static String traverse(Graph g) {
        List<Node> nodeList = g.getNodeList();

        // Start with the first node
        Node n = nodeList.get(0);
        BreadthFirstTraverse(g, n);

        // Print Traversal order
        String str = getTraversalOrder();
        System.out.println(str);
        return str;
    }

    private static void BreadthFirstTraverse(Graph g, Node n) {

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
                System.out.println(getTraversalOrder());
                List<Node> nodeList = g.getAdjacentNodes(nextNode);
                tempQueue.addAll(nodeList);
            }
        }
    }

    private static String getTraversalOrder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < traversalOrder.size(); i++) {
            String name = traversalOrder.get(i).getName();
            sb.append(name + " ");
        }
        return sb.toString();
    }
}
