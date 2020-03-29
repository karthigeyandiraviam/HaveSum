package com.ddk;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayManupulation {
    static void printArray(long[] array) {
        for ( long a : array ) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static long debugArrayManupulation(int n, int[][] queries) {
        long[] array = new long[n];
        long maxNum = 0;
        for ( int i = 0 ; i < queries.length ; i++ ) {
            for ( int j = queries[i][0]-1 ; j <= (queries[i][1]-1) ; j++ ) {
                array[j] += queries[i][2];
                maxNum = (maxNum < array[j])?array[j]:maxNum;
            }
            printArray(array);
        }
        return maxNum;
    }

    static long arrayManipulation(int n, int[][] queries) {
        long[] array = new long[n + 2];
        for ( int i = 0 ; i < queries.length ; i++ ) {
            array[queries[i][0]] += queries[i][2];
            array[queries[i][1]+1] -= queries[i][2];
        }
        long maxNum = array[0];
        for ( int i = 1 ; i < array.length ; i++ ) {
            array[i] += array[i-1];
            maxNum = (maxNum < array[i])?array[i]:maxNum;
        }
        printArray(array);
        return maxNum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = debugArrayManupulation(n, queries);
        System.out.println(result);

        result = arrayManipulation(n, queries);
        System.out.println(result);

        scanner.close();
    }
}
