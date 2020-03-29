package com.ddk;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class minSwap {
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int swap = 0;
        for ( int i = 0 ; i < arr.length ; i++ ) {
            if ( arr[i] != (i+1) ) {
                for ( int j = i+1; j < arr.length ; j++ ) {
                    if ( arr[j] == (i+1) ) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        swap++;
                        break;
                    }
                }
            }
        }
        return swap;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        System.out.println(res);

        scanner.close();
    }
}
