package com.gk.algorithm.unknow;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author akun
 * @date 2019-03-31
 * 数组实现循环队列
 */
@Slf4j
public class CircularQueue {

    @Getter
    private String[] items;
    private int capacity;
    private int head = 0;
    private int tail = 0;


    public CircularQueue(int capacity) {
        items = new String[capacity];
        this.capacity = capacity;
    }

    public boolean push(String item) {
        if ((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;
    }

    public String poll() {
        if (head == tail) {
            return null;
        }
        String item = items[head];
        head = (head + 1) % capacity;
        return item;
    }

    public static void main(String[] args) {
        int capacity = 8;
        CircularQueue queue = new CircularQueue(capacity);

        for (int i = 0; i < capacity; i++) {
            boolean pushed = queue.push("i:" + i);
            log.info("pushed{}:{}", i, pushed);
            if ((i & 1) == 1) {
                String polled = queue.poll();
                log.info("polled{}:{}", i, polled);
            }
        }
        log.info("{}", Arrays.toString(queue.getItems()));
    }


}
