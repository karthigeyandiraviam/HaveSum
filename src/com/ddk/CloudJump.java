package com.ddk;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class CloudJump {
    static int jumpingOnClouds(int[] c) {
        int steps = 0;
        Map<Integer, Integer> counter = new Hashtable<>();
        for ( int n : c ) {
            if ( n == 1 ) {
                steps++;
                counter.put(0, 0);
            } else {
                counter.put(0, counter.containsKey(0)?counter.get(0)+1:1);
                if ( counter.get(0) % 2 == 0 )
                    steps++;
            }
        }
        return steps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        System.out.println(result);
        scanner.close();
    }
}
