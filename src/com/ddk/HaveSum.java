package com.ddk;

import java.util.*;
import java.io.*;

/*
** Given a list of numbers - any order
** Given a number to represent Total
** Find first 2 numbers whose sum is equal to Total
** Example: [1,2,4,4] Total: 8 -> should print 4 and 4
** Example: [-1,0,1,2] Total: 0 -> Should print -1 and 1
** Example: [1,2,3,4] Total: 8 - should print nothing
 */
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

    public void printSet(HashMap<Integer, Integer> comp) {
        comp.keySet().stream().forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    /*
    ** Example: [1,2,4,4] Total: 8
    ** Iter 1: number: 1, there's nothing in comp, add 8-1 to comp, comp: {7}
    ** Iter 2: number: 2, 2 is not there in comp, add 8-2 to comp, comp: {7,6}
    ** Iter 3: number: 4, 4 is not there in comp, add 8-4 to comp, comp: {7,6,4}
    ** Iter 4: number: 4, 4 exist in comp - print 4 and (8-4) return true
     */
    /*
     ** Example: [7,5,3,1,-1] Total: 4
     ** Iter 1: number: 7, there's nothing in comp, add 4-7 to comp, comp: {-3}
     ** Iter 2: number: 5, 5 is not there in comp, add 4-5 to comp, comp: {-3,-1}
     ** Iter 3: number: 3, 3 is not there in comp, add 4-3 to comp, comp: {-3,-1,1}
     ** Iter 4: number: 1, 1 exist in comp - print 1 and (4-1) return true
     */
    public boolean findHaveSum() {
        HashMap<Integer, Integer> comp = new HashMap();

        for ( int i = 0 ; i < numbers.size() ; i++ ) {
            if ( comp.containsKey(numbers.get(i)) ) {
                System.out.println(numbers.get(i) + " and " + (sum-numbers.get(i)));
                //printSet(comp);
                return true;
            }
            comp.put(sum - numbers.get(i), i);
            printSet(comp);
        }
        //printSet(comp);
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

