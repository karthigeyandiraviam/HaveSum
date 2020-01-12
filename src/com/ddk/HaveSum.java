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

    public void printSet(HashSet<Integer> comp) {
        for ( Integer c : comp ) {
            System.out.println(c);
        }
    }

    public boolean findHaveSum() {
        HashSet<Integer> comp = new HashSet();

        for ( Integer number : numbers ) {
            if ( comp.contains(number) ) {
                System.out.println(number + " and " + (sum-number));
                printSet(comp);
                return true;
            }
            comp.add(sum - number);
        }
        printSet(comp);
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

