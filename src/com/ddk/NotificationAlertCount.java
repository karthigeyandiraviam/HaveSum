package com.ddk;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NotificationAlertCount {

    static void printArray(int[] A) {
        for ( int i = 0 ; i < A.length ; i++ )
            System.out.print("[" + i + "]:" + A[i] + " ");
        System.out.println();
    }

    static double getMedian(int[] count, int d) {
        int[] countfreq = Arrays.copyOf(count, count.length);

        for(int i=1; i< countfreq.length; i++) {
            countfreq[i] += countfreq[i-1];
        }

        double median;
        int a = 0;
        int b = 0;

        //d is even -> median = a+b
        if(d%2==0){
            int first = d/2;
            int second = first+1;
            int i = 0;
            for(;i<countfreq.length;i++){
                if(first<=countfreq[i]){
                    a = i;
                    break;
                }
            }
            for(;i<countfreq.length;i++){
                if(second<=countfreq[i]){
                    b = i;
                    break;
                }
            }
        }
        else    //d is odd -> median = a + 0 = 2*(middle elem)
        {
            int first = d/2 + 1;
            for(int i=0;i<countfreq.length;i++){
                if(first<=countfreq[i]){
                    a = 2*i;
                    break;
                }
            }
        }
        median = a + b;
        return median;
    }

    // Complete the activityNotifications function below.
    static int activityNotifications1(int[] expenditure, int d) {
        int notification = 0;
        if ( expenditure.length > d ) {
            int[] count = new int[256];
            for ( int i = 0 ; i < d ; i++ )
                count[expenditure[i]]++;
            for ( int i = d ; i < expenditure.length ; i++ ) {
                double median = getMedian(count, d);
                System.out.println("Median: " + median);
                System.out.println("if (" + expenditure[i] + " >= " + median + ")");
                if ( expenditure[i] >= median )
                    notification++;
                System.out.println("Notification: " + notification);
                count[expenditure[i]]++;
                count[expenditure[i-d]]--;
            }
        }
        return notification;
    }
    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int notification = 0;
        if ( expenditure.length > d ) {
            int[] medianArray = Arrays.stream(expenditure).sorted().toArray();
            printArray(medianArray);
            for ( int i = d ; i < expenditure.length ; i++ ) {
                int midPoint = (i + (i-d))/2;
                System.out.println("medianArray[" + midPoint + "]: " + medianArray[midPoint]);
                double median = medianArray[midPoint] * 1.0;
                System.out.println("Median: " + median);
                if (d % 2 == 0) {
                    System.out.println("medianArray[" + (midPoint-1) + "]: " + medianArray[midPoint-1]);
                    System.out.print("(" + median + " + " + medianArray[midPoint-1] + ") / 2.0 = ");
                    median = (median + medianArray[midPoint-1]) / 2.0;
                    System.out.println(median);
                }
                System.out.println("if (" + expenditure[i] + " >= " + (median*2.0) + ")");
                if ( expenditure[i] >= (median*2.0) )
                    notification++;
                System.out.println("Notification: " + notification);
            }
        }
        return notification;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications1(expenditure, d);

        System.out.println(result);

        scanner.close();
    }
}

