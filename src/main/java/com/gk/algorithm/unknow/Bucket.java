package com.gk.algorithm.unknow;

import java.util.Scanner;

public class Bucket {

    public static void main(String[] args) {

        bucket();
    }

    public static void bucket() {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[11];
        for (int i = 0; i < a.length; i++) {
            int t = scanner.nextInt();
            a[t]++;
        }
        scanner.close();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i]; j++) {
                System.out.print(i + " ");
            }
        }
    }
}
