package com.ddk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GenRand {

    public static void main(String[] args) {
        Random r = new Random();
        HashSet<Integer> rNums = new HashSet<>();
        while ( rNums.size() < 30 ) {
            rNums.add(r.nextInt(48) + 50);
        }

        Double[] samples = new Double[30];
        int count = 10, i = 0;
        for ( Integer n : rNums ) {
            samples[i] = (n/100.0);
            System.out.print(String.format("%.2f ", samples[i]));
            i++;
            count--;
            if ( count == 0 ) {
                //System.out.println();
                count = 10;
            }
        }
        System.out.println();
        System.out.println("Sum: " + Arrays.stream(samples).mapToDouble(Double::doubleValue).sum() + ", Size: " + samples.length);
        System.out.println("Mean: " + Arrays.stream(samples).mapToDouble(Double::doubleValue).sum() / samples.length);

/*
        Integer[] rNums = {229, 707, 311, 438, 190, 860, 905, 928, 072, 902, 829, 363, 382, 277, 290, 046, 736, 175, 409, 367, 629, 866, 84, 886, 830, 992, 423, 153, 617, 625};

        Arrays.stream(rNums).forEach(a -> System.out.print(a + " "));
        System.out.println();
        System.out.println("Sum: " + Arrays.stream(rNums).mapToInt(Integer::intValue).sum() + ", Size: " + rNums.length);
        System.out.println("Mean: " + Arrays.stream(rNums).mapToInt(Integer::intValue).sum() / rNums.length);
*/
    }
}
