package com.ownerkaka.testjdk.algorithm.unknow;

import java.util.LinkedList;
import java.util.Queue;

public class TestQuene {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(6);
        queue.add(3);
        queue.add(1);
        queue.add(7);
        queue.add(5);
        queue.add(8);
        queue.add(9);
        queue.add(2);
        queue.add(4);
        int num = queue.size();
        for (int i = 0; i < num; i++) {
            System.out.print(queue.remove() + " ");
            if (!queue.isEmpty())
                queue.add(queue.remove());

        }
    }

}
