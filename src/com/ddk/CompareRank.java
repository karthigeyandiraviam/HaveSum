package com.ddk;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompareRank {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] uniqueScores = IntStream.of(scores).boxed().sorted(Comparator.reverseOrder()).distinct().mapToInt(i -> i).toArray();
        int[] aliceRanks = new int[alice.length];
        for ( int i = 0 ; i < alice.length ; i++ ) {
            if ( alice[i] >= uniqueScores[0] ) {
                aliceRanks[i] = 1;
            } else if ( alice[i] < uniqueScores[uniqueScores.length-1] ) {
                aliceRanks[i] = uniqueScores.length+1;
            } else if ( alice[i] == uniqueScores[uniqueScores.length-1] ) {
                aliceRanks[i] = uniqueScores.length;
            } else {
                for ( int j = 1; j < uniqueScores.length ; j++ ) {
                    if ( alice[i] >= uniqueScores[j] ) {
                        aliceRanks[i]=j+1;
                        break;
                    }
                }
            }
        }
        return aliceRanks;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
