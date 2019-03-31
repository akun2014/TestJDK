package com.gk.algorithm.unknow;

public class Joseph {

    public static void main(String[] args) {

        System.out.println(joseph(6, 2));
    }

    public static int joseph(int n, int m) {
        int fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = (fn + m) % i;
        }
        return fn;
    }

}
