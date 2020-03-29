package com.ddk;

public class RotateArray {
    static int[] rotateArray(int[] A, int K) {
        if ( A.length > 0 ) {
            for (int i = 0; i < K; i++) {
                int last = A[A.length - 1];
                for (int j = A.length - 1; j > 0; j--) {
                    A[j] = A[j - 1];
                }
                A[0] = last;
            }
        }
        return A;
    }

    static void printArray(int[] A) {
        for ( int a : A ) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = {3, 8, 9, 7, 6};
        int K = 2;
        printArray(A);
        int[] B = rotateArray(A, K);
        printArray(B);
    }
}
