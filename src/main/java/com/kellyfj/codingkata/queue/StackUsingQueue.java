package com.kellyfj.codingkata.queue;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> temp = new LinkedList<Integer>();

    public int pop() {
        if (q1.peek() == null) { // The stack is empty, nothing to return
            return 0;
        } else {
            int pop = q1.remove();
            return pop;
        }
    }

    public void push(int data) {

        if (q1.peek() == null) {
            q1.add(data);
        } else {
            int q1Size = q1.size();
            for (int i = 0; i < q1Size; i++) {
                temp.add(q1.remove());
            }
            q1.add(data);
            int tmpSize = temp.size();
            for (int j = 0; j < tmpSize; j++) {
                q1.add(temp.remove());
            }

        }
    }
}
