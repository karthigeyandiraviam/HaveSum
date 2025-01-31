package com.ddk;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TwoStrings {
    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        LinkedHashMap<Character, Integer> s1Map = new LinkedHashMap<>();
        for ( int i = 0 ; i < s1.length() ; i++ ) {
            s1Map.put(s1.charAt(i), (s1Map.containsKey(s1.charAt(i))?s1Map.get(s1.charAt(i))+1:1));
        }
        for ( int i = 0 ; i < s2.length() ; i++ ) {
            if ( s1Map.containsKey(s2.charAt(i)) )
                return "YES";
        }
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }}
