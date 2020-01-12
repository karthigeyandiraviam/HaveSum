package com.ddk;

import java.util.*;
import java.io.*;

class HaveSum {
    List<Integer> numbers;
    Integer sum;

    public HaveSum() {
        numbers = new ArrayList<Integer>();
        sum = -1;
    }

    public void readAndInit() {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in) );
        try {
            System.out.println("How many numbers >>");
            int count = new Integer(reader.readLine());

            System.out.println("Enter numbers (one number in a line)>>");
            while ( count > 0 ) {
                numbers.add(new Integer(reader.readLine()));
                count--;
            }
            System.out.println("Enter Sum >>");
            sum = new Integer(reader.readLine());
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void printNumbers() {
        System.out.println("Numbers >>");
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public void printSum() {
        System.out.println("Sum >>");
        System.out.println(sum);
    }

    public boolean findHaveSum() {
        int start = 0;
        int end = numbers.size() - 1;
        while (start < end) {
            if ( numbers.get(start) + numbers.get(end) == sum ) {
                System.out.println("numbers[" + start + "]=" + numbers.get(start) + " and numbers[" + end + "]=" + numbers.get(end));
                return true;
            } else if ( numbers.get(start) + numbers.get(end) > sum )
                end--;
            else if ( numbers.get(start) + numbers.get(end) < sum )
                start++;
        }
        return false;
    }

    public static void main(String args[]) {
        HaveSum haveSum = new HaveSum();
        haveSum.readAndInit();
        // haveSum.printNumbers();
        // haveSum.printSum();
        haveSum.findHaveSum();
    }
}

