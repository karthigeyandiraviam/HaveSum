package com.ddk;

import java.util.Arrays;

public class FlipCoins {
    static int solution(int[] A) {
        // write your code in Java SE 8
        if ( A.length == 1 )
            return 0;
        if (A.length == 2) {
            if (A[0] == A[1])
                return 1;
            else
                return 0;
        }

        int count = 0;
        boolean isSeq = true; // Assume all bits are in sequence
        for ( int i = 0 ; i < A.length-2 ; i++ ) {
            if ( A[i] != A[i+2] ) {
                if ( i % 2 == 0 )
                    A[i] = A[i + 2];
                else
                    A[i + 2] = A[i];
                count++;
            } else if ( A[i] != A[i+1] )
                isSeq = false; // Not all bits are in sequence
        }

        // if all bits are in sequence, count will half of length of A
        if ( count == 0 && isSeq )
            count = A.length / 2;

        return count;
    }

    static int solution1(int[] A) {
        int count1 = 0;
        int count2 = 0;
        // Result to start with 0
        int[] B = Arrays.copyOf(A, A.length);
        int expected = 0;
        Arrays.stream(A).forEach(a -> System.out.print(a + " "));
        System.out.println();
        for ( int i = 0 ; i < A.length ; i++ ) {
            if ( A[i] != expected ) {
                A[i] = expected;
                count1++;
            }
            expected = (expected == 0)?1:0;
        }
        Arrays.stream(A).forEach(a -> System.out.print(a + " "));
        System.out.println();
        expected = 1;
        for ( int i = 0 ; i < B.length ; i++ ) {
            if ( B[i] != expected ) {
                B[i] = expected;
                count2++;
            }
            expected = (expected == 0)?1:0;
        }
        Arrays.stream(B).forEach(a -> System.out.print(a + " "));
        System.out.println();
        System.out.println("Num Flips " + count1);
        System.out.println("Num Flips " + count2);
        return Math.min(count1, count2);
    }

    public static void main(String[] args) {
        int[] A = {0,0,1,0,0,0,0};
        System.out.println(solution1(A));
    }
}
