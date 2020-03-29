package com.ddk;

import com.sun.tools.javac.util.ArrayUtils;
import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyMatrix {
    public static void findOccurenceInMatrix(
            List<List<String>> records, String element,
            Pair<Integer, Integer> currPair,
            Set<Pair<Integer, Integer>> pairs
    ) {
        for ( int i = 0 ; i < records.size() ; i++ ) {
            for ( int j = 0 ; j < records.get(i).size() ; j++ ) {
                if ( !(currPair.getKey() == i && currPair.getValue() == j) ) {
                    if (records.get(i).get(j).equals(element)) {
                        pairs.add(new Pair(i, j));
                    }
                }
            }
        }
    }
    public static List<List<Integer>> adjacencyMatrix(List<List<String>> records) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Pair<Integer, Integer>> pairs = new HashSet<>();

        int i = 0, j = 0;
        for ( i = 0 ; i < records.size() ; i++ ) {
            for ( j = 0 ; j < records.get(i).size() ; j++ ) {
                findOccurenceInMatrix(records, records.get(i).get(j), new Pair(i,j), pairs);
            }
        }

        int maxSize = (i > j)? i : j;
        int[][] cost = new int[maxSize][maxSize];
        for ( i = 0 ; i < maxSize ; i++ ) {
            for ( j = 0 ; j < maxSize ; j++ ) {
                cost[i][j] = 0;
            }
        }

        for ( Pair<Integer, Integer> p : pairs ) {
            cost[p.getKey()][p.getValue()] = 1;
        }

        for ( i = 0 ; i < maxSize ; i++ ) {
            result.add(Arrays.stream(cost[i]).boxed().collect(Collectors.toList()));
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        List<List<String>> s = new ArrayList<List<String>>();
        System.out.println("Enter number of rows");
        int numRow = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numRow; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            s.add(Arrays.asList(sRowItems));
        }
        for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < s.get(i).size(); j++) {
                System.out.print(s.get(i).get(j));
            }
            System.out.println();
        }


        List<List<Integer>> result = adjacencyMatrix(s);

        for ( List<Integer> r : result ) {
            for ( Integer i : r ) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        scanner.close();
    }
}
