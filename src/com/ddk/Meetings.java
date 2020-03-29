package com.ddk;

import java.util.*;

/*

We are writing a tool to help users manage their calendars. Given an unordered list of times of day when someone is busy, write a function that tells us whether they're available during a specified period of time.

Each time is expressed as an integer using 24-hour notation, such as 1200 (12:00), 1530 (15:30), or 800 (8:00).

Sample input:

meetings = [
  [1230, 1300],
  [845, 900],
  [1300, 1500]
]

Expected output:

isAvailable(meetings, 915, 1215)   => true
isAvailable(meetings, 900, 1230)   => true
isAvailable(meetings, 850, 1240)   => false
isAvailable(meetings, 1200, 1300)  => false
isAvailable(meetings, 700, 1600)   => false
isAvailable(meetings, 800, 845)    => true
isAvailable(meetings, 1500, 1800)  => true
isAvailable(meetings, 845, 859)    => false
isAvailable(meetings, 846, 900)    => false
isAvailable(meetings, 846, 859)    => false
isAvailable(meetings, 2359, 2400)  => true

n = number of meetings
m = minutes in range of meetings


*/

/* Sorted order
      { 845,  900},
      {1230, 1300},
      {1300, 1500},
*/

public class Meetings {

    public static void sortMeetings(int[][] meetings) {
        // Sort Meeting times
        Map<Integer, Integer> availableTime = new TreeMap<Integer, Integer>();
        for ( int i = 0 ; i < meetings.length ; i++ ) {
            availableTime.put(meetings[i][0], meetings[i][1]);
        }

        int i = 0;
        for ( Map.Entry<Integer, Integer> entry : availableTime.entrySet() ) {
            meetings[i][0] = entry.getKey();
            meetings[i][1] = entry.getValue();
            i++;
        }
    }

    public static boolean isAvailable(int[][] meetings, int start, int end) {
        for ( int i = 0 ; i < meetings.length ; i++ ) {
            if ( start > meetings[i][0] && start < meetings[i][1] )
                return false;
            if ( end > meetings[i][0] && end <= meetings[i][1] )
                return false;
        }
        if ( start <= meetings[0][0] && end > meetings[meetings.length-1][1] )
            return false;
        return true;
    }

    public static void main(String[] argv) {
        int[][] meetings = {
                {1230, 1300},
                { 845,  900},
                {1300, 1500},
        };
        sortMeetings(meetings);
        System.out.println(isAvailable(meetings, 700, 1600)); // => false
        System.out.println(isAvailable(meetings, 900, 1230)); // => true
        System.out.println(isAvailable(meetings, 915, 1215)); // => true
        System.out.println(isAvailable(meetings, 850, 1240)); // => false
        System.out.println(isAvailable(meetings, 1200, 1300)); // => false
        System.out.println(isAvailable(meetings, 800, 845)); // => true
        System.out.println(isAvailable(meetings, 1500, 1800)); // => true
        System.out.println(isAvailable(meetings, 845, 859)); // => false
        System.out.println(isAvailable(meetings, 846, 900)); // => false
        System.out.println(isAvailable(meetings, 846, 859)); // => false
        System.out.println(isAvailable(meetings, 2359, 2400)); // => true
    }
}
