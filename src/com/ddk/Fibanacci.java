package com.ddk;

public class Fibanacci {
    public static void fibIteration(int prev, int a, int max) {
        for ( int i = 0 ; i < max ; i++ ) {
            System.out.print(prev + " ");
            int b = prev;
            prev = a;
            a += b;
        }

    }

    public static void fibRec(int prev, int a, int max) {
        if ( max >= 1 ) {
            System.out.print(prev + " ");
            fibRec(a, (prev + a), max-1);
        }
    }

    public static void main(String[] args) {
        int max = 10;
        fibIteration(0, 1, max);
        System.out.println();
        fibRec(0, 1, max);
    }
}
