package com.ddk;

/*
// PART 1

// Imagine you have a camera installed in your building which is able to detect motion. The camera outputs a stream of timestamps and a value indicating the motion intensity at that time.
// You collect this data and want to analyze it to determine the periods of time at which motion was at or above a certain threshold.

// Write a function which takes an array of sorted `(timestamp, motion_intensity)` pairs and then outputs the periods of time where motion was at or above a certain `threshold` (which is between 0 and 1). Your output should be an array of timestamp pairs where the first value is the start of the period above the threshold and the last value is the end of that period (inclusive).

// answer = motion_periods_for_camera(
//     [(1, 0.4), (5, 0.2), (11, 0.9), (15, 0.9), (17, 0.8), (25, 0.5), (27, 0.8), (36, 0.9)],
//     0.8
// )

// assert answer == [(11, 17), (27, 36)]

*/


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

class FindThreshold {

    static List<Pair<Integer, Integer>> motion_periods_for_camera(
            List<Pair<Integer, Double>> motions,
            Double threshold
    ) {
        // [(1, 0.4), (5, 0.2), (11, 0.9), (15, 0.9), (17, 0.8), (25, 0.5), (27, 0.8), (36, 0.9)]
        Integer key = null;
        List<Pair<Integer, Integer>> answer = new ArrayList<>();
        Pair<Integer, Double> previousTuple = null;
        for ( Pair<Integer, Double> tuple : motions ) {
            if ( tuple.getValue() >= threshold ) {
                if ( key == null )
                    key = tuple.getKey();
            }
            if ( key != null &&
                    tuple.getValue() < threshold &&
                    previousTuple != null ) {
                answer.add(new Pair(key, previousTuple.getKey()));
                key = null;
            }
            previousTuple = tuple;
        }
        if ( key != null && previousTuple != null ) {
            answer.add(new Pair(key, previousTuple.getKey()));
        }
        return answer;
    }

    public static void main(String[] args) {
        // [(1, 0.4), (5, 0.2), (11, 0.9), (15, 0.9), (17, 0.8), (25, 0.5), (27, 0.8), (36, 0.9)]
        List<Pair<Integer, Double>> tuples = new ArrayList<>();
        tuples.add(new Pair(1,0.4));
        tuples.add(new Pair(5,0.2));
        tuples.add(new Pair(11,0.9));
        tuples.add(new Pair(15,0.9));
        tuples.add(new Pair(17,0.8));
        tuples.add(new Pair(25,0.5));
        tuples.add(new Pair(27,0.8));
        tuples.add(new Pair(36,0.9));

        List<Pair<Integer, Integer>> answer = motion_periods_for_camera(tuples, 0.8);
        for ( Pair<Integer, Integer> a :  answer ) {
            System.out.println(a.getKey() + " => " + a.getValue());
        }
    }
}
