package com.ddk;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class RansomNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        LinkedHashMap<String, Integer> magMap = new LinkedHashMap<>();
        for ( String mag : magazine ) {
            magMap.put(mag, (magMap.containsKey(mag)?magMap.get(mag)+1:1));
        }

        LinkedHashMap<String, Integer> noteMap = new LinkedHashMap<>();
        for ( String n : note ) {
            noteMap.put(n, (noteMap.containsKey(n)?noteMap.get(n)+1:1));
        }

        boolean isFound = true;
        for ( Map.Entry<String, Integer> entry : noteMap.entrySet() ) {
            if ( ! (magMap.containsKey(entry.getKey()) && magMap.get(entry.getKey()) >= entry.getValue()) ) {
                isFound = false;
                break;
            }
        }
        if ( isFound )
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
