package com.ddk;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

/*
Requirements:
 - Finish implementing the function "largestMeeting" below to find the largest meeting that can be scheduled.
 - Largest meeting is defined as the most number of people in a block of time. If there are ties, choose the meeting with the longest duration.
 - A meeting can only span multiple hours if the same people can attend.
 - There are three separate test cases you must solve for: schedules1, schedules2, and schedules3.
 - Each of these schedules represent the schedule of five people schedules[0]: one person, schedules[1]: a second person, etc. Each individual person's schedule contains meetings, which denote when a person is busy. The first element represents the start hour of the meeting and the second element represents the end hour of the meeting. All meetings start and end on the hour.
 - The result should print the number of people in the largest meeting along with the meeting's start and end time.
 - All times in the problem have an inclusive start and exclusive end unless otherwise specified.
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Meeting {
    public Integer start;
    public Integer end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean isBusy(int hour){
        return (start <= hour && end > hour);
    }

    @Override
    public boolean equals(Object meeting) {
        if (this == meeting) {
            return true;
        }
        if (meeting == null || getClass() != meeting.getClass()) {
            return false;
        }
        Meeting m = (Meeting) meeting;
        return (this.start == m.start && this.end == m.end);
    }

    @Override
    public int hashCode() {
        int hash = this.start.hashCode();
        hash = hash * 31 + this.end.hashCode();
        return hash;
    }

}

class Schedule {
    public List<Meeting> meetings;

    public Schedule(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}

public class FindLargestMeeting {
    public static void main(String[] args) {
    /*
    Visual Representation:
      - In the visual, red squares represent when they are busy.
      - https://s3.amazonaws.com/zeus-test-assets/schedule/sample-schedule
    Expected Output: The largest meeting you can have is with 3 people between 4 and 6.
    */
        int[][][] scheduleInputs1 = {
                {{0,3},{3,4},{6,8},{9,10},{10,13},{13,15},{17,19},{20,22},{22,24}},
                {{0,2},{2,4},{9,10},{10,12},{16,17},{19,21},{23,24}},
                {{4,5},{5,8},{14,15},{15,16},{18,19},{19,22}},
                {{6,9},{9,11},{12,15},{15,16},{21,23},{23,24}},
                {{0,1},{1,4},{4,7},{7,8},{8,10},{10,13},{13,14},{15,18},{19,21}}
        };
        List<Schedule> schedules1 = getSchedules(scheduleInputs1);

    /*
    Visual Representation:
      - In the visual, red squares represent when they are busy.
      - https://s3.amazonaws.com/zeus-test-assets/scheduling-case2.png
    Expected Output: The largest meeting you can have is with 3 people between 16 and 19.
    */
        int[][][] scheduleInputs2 = {
                {{0,3},{3,4},{6,8},{9,10},{10,13},{13,15},{16,19},{20,22},{22,24}},
                {{0,2},{2,4},{9,10},{10,12},{19,21},{23,24}},
                {{4,5},{5,8},{14,15},{19,22}},
                {{6,9},{9,11},{12,15},{15,16},{21,23},{23,24}},
                {{0,1},{1,4},{4,7},{7,8},{8,10},{10,13},{13,14},{15,19},{19,21}}
        };
        List<Schedule> schedules2 = getSchedules(scheduleInputs2);

    /*
    Visual Representation:
      - In the visual, red squares represent when they are busy.
      - https://s3.amazonaws.com/zeus-test-assets/scheduling-case3.png
    Expected Output: The largest meeting you can have is with 3 people between 21 and 24.
    */
        int[][][] scheduleInputs3 = {
                {{0,3},{3,4},{6,8},{9,10},{10,13},{13,15},{17,19},{20,22},{22,24}},
                {{0,2},{2,4},{9,10},{10,12},{16,17},{19,21}},
                {{4,5},{5,8},{14,15},{15,16},{18,19},{19,21}},
                {{6,9},{9,11},{12,15},{15,16},{21,23},{23,24}},
                {{0,1},{1,4},{4,7},{7,8},{8,10},{10,13},{13,14},{15,18},{19,21}}
        };
        List<Schedule> schedules3 = getSchedules(scheduleInputs3);

        largestMeeting(schedules1);
        largestMeeting(schedules2);
        largestMeeting(schedules3);
    }

    public static List<Schedule> getSchedules(int[][][] scheduleInputs) {
        List<Schedule> schedules = new ArrayList<Schedule>();

        for (int[][] scheduleInput : scheduleInputs) {
            List<Meeting> meetings = new ArrayList<Meeting>();
            for (int [] meeting_times : scheduleInput) {
                meetings.add(new Meeting(meeting_times[0], meeting_times[1]));
            }
            schedules.add(new Schedule(meetings));
        }

        return schedules;
    }

    public static void largestMeeting(List<Schedule> scheduleList) {
        int people = 0;
        int start = 0;
        int end = 0;

        List<Schedule> freeSchedules = new ArrayList<>();
        //
        // add your code here
        //
        for ( int i = 0 ; i < scheduleList.size() ; i++ ) {
            List<Meeting> freeSlots = new ArrayList<>();
            if ( scheduleList.get(i).meetings.get(0).start != 0 )
                freeSlots.add(new Meeting(0, scheduleList.get(i).meetings.get(0).start));
            for ( int j = 0 ; j < scheduleList.get(i).meetings.size()-1 ; j++ ) {
                if ( scheduleList.get(i).meetings.get(j+1).start - scheduleList.get(i).meetings.get(j).end > 0 )
                    freeSlots.add(new Meeting(scheduleList.get(i).meetings.get(j).end, scheduleList.get(i).meetings.get(j+1).start));
            }
            if ( scheduleList.get(i).meetings.get(scheduleList.get(i).meetings.size()-1).end != 24 )
                freeSlots.add(new Meeting(scheduleList.get(i).meetings.get(scheduleList.get(i).meetings.size()-1).end, 24));

            if ( freeSlots.size() > 0 )
                freeSchedules.add(new Schedule(freeSlots));
        }

        Map<Meeting, Integer> possibleMeeting = new Hashtable<>();
        for ( int j = 0 ; j < freeSchedules.size() ; j++ ) {
            List<Meeting> firstFreeSlots = freeSchedules.get(0).meetings;
            for ( int i = 0 ; i < firstFreeSlots.size() ; i++ ) {
                for ( int k = 0 ; k < freeSchedules.get(j).meetings.size() ; k++ ) {
                    if (firstFreeSlots.get(i).start >= freeSchedules.get(j).meetings.get(k).start ||
                        firstFreeSlots.get(i).end <= freeSchedules.get(j).meetings.get(k).end
                    ) {
                        int start1 = (firstFreeSlots.get(i).start >= freeSchedules.get(j).meetings.get(k).start)?firstFreeSlots.get(i).start:freeSchedules.get(j).meetings.get(k).start;
                        int end1 = (firstFreeSlots.get(i).end <= freeSchedules.get(j).meetings.get(k).end)?firstFreeSlots.get(i).end:freeSchedules.get(j).meetings.get(k).end;
                        int dur1 = end1 - start1;
                        if ( dur1 > 0 ) {
                            Meeting m = new Meeting(start1, start1 + dur1-1);
                            possibleMeeting.put(m, (possibleMeeting.containsKey(m)) ? possibleMeeting.get(m)+1 : 1);
                        }
                    }
                }
            }
        }

        Pair<Meeting, Integer> longestMeeting = null;
        for ( Map.Entry<Meeting, Integer> entry : possibleMeeting.entrySet() ) {
            if ( longestMeeting == null ) {
                longestMeeting = new Pair(entry.getKey(), entry.getValue());
            } else {
                if ( longestMeeting.getValue() < entry.getValue() )
                    longestMeeting = new Pair(entry.getKey(), entry.getValue());
                else if ( longestMeeting.getValue() == entry.getValue() ) {
                    int dur = entry.getKey().end - entry.getKey().start;
                    if ( (longestMeeting.getKey().end - longestMeeting.getKey().start) < dur )
                        longestMeeting = new Pair(entry.getKey(), entry.getValue());
                }
            }
            System.out.println("Start " + entry.getKey().start + ", End " + entry.getKey().end + ", Num People " + entry.getValue());
        }
        people = longestMeeting.getValue();
        start = longestMeeting.getKey().start;
        end = longestMeeting.getKey().end;
        System.out.println(String.format("The largest meeting is with %s people between %s and %s", people, start, end));
    }
}
