package com.ddk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
** Given a list of numbers - any order
** Given a number to represent Total
** Find first 2 numbers whose sum is equal to Total
** Example: [1,2,4,4] Total: 8 -> should print 4 and 4
** Example: [-1,0,1,2] Total: 0 -> Should print -1 and 1
** Example: [1,2,3,4] Total: 8 - should print nothing
 */
class TwoSum {

    static int[] twoSum(int[] numbers, int sum) {
        HashMap<Integer, Integer> comp = new HashMap();
        int[] ret = new int[2];
        for ( int i = 0 ; i < numbers.length ; i++ ) {
            if ( comp.containsKey(numbers[i]) ) {
                System.out.println(numbers[i] + " and " + (sum-numbers[i]));
                ret[0] = comp.get(numbers[i]);
                ret[1] = i;
                break;
            }
            comp.put(sum - numbers[i], i);
        }
        return ret;

    }

    public static void main(String args[]) {
        List<Integer> numbers = new ArrayList<>();
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
            int sum = new Integer(reader.readLine());
            int[] ret = twoSum(numbers.stream().mapToInt(i -> i).toArray(), sum);
            Arrays.stream(ret).forEach(i -> System.out.print(i + " "));
            System.out.println();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

