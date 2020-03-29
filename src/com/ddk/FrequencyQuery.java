package com.ddk;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

public class FrequencyQuery {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(int[][] queries) {
        Map<Integer, Integer> counter = new Hashtable<>(queries.length);
        Map<Integer, Integer> freqMap = new Hashtable<>();
        List<Integer> output = new ArrayList<>(queries.length);
        try {
            for (int i = 0; i < queries.length; i++) {
                int op = queries[i][0];
                int val = queries[i][1];
                Integer mapVal = counter.get(val);
                if (op == 1) {
                    if (null != mapVal) {
                        if (freqMap.get(mapVal) > 1)
                            freqMap.put(mapVal, freqMap.get(mapVal) - 1);
                        else
                            freqMap.remove(mapVal);
                    }
                    counter.put(
                            val,
                            (null != mapVal) ? mapVal + 1 : 1
                    );
                    freqMap.put(
                            counter.get(val),
                            (null != freqMap.get(counter.get(val))) ? freqMap.get(counter.get(val)) + 1 : 1
                    );
                } else if (op == 2) {
                    if (null != mapVal) {
                        if (mapVal > 1)
                            counter.put(
                                    val,
                                    mapVal - 1
                            );
                        else
                            counter.remove(val);
                        if (null != freqMap.get(mapVal)) {
                            if (freqMap.get(mapVal) > 1)
                                freqMap.put(
                                        mapVal,
                                        freqMap.get(mapVal) - 1
                                );
                            else
                                freqMap.remove(mapVal);
                        }
                    }
                } else if (op == 3) {
                    if (null != freqMap.get(val))
                        output.add(1);
                    else
                        output.add(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        int[][] queries = new int[q][2];

        for ( int i = 0 ; i < q ; i++ ) {
            String[] query = bufferedReader.readLine().split(" ");
            queries[i][0] = Integer.parseInt(query[0]);
            queries[i][1] = Integer.parseInt(query[1]);
        }

        List<Integer> ans = freqQuery(queries);

        System.out.println(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
    }
}
