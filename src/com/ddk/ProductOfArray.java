package com.ddk;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductOfArray {
    public static int[] prod_ex( int[] input ) {
        if ( input == null || input.length == 0 )
            return new int[]{};
        if ( input.length == 1 ) {
            return new int[]{1};
        } else {
            List<Integer> output = new ArrayList<>();
            for ( int i = 0 ; i < input.length ; i++ ) {
                int prd = 1;
                for ( int j = 0 ; j < input.length ; j++ ) {
                    if ( i != j ) {
                        prd *= input[j];
                    }
                }
                output.add(prd);
            }
            return output.stream().mapToInt(i -> i).toArray();
        }
    }

    public static int[] prod_ex_1( int[] input ) {
        if ( input == null || input.length == 0 )
            return new int[]{};
        if ( input.length == 1 ) {
            return new int[]{1};
        } else {
            int[] output = new int[input.length];
            output[input.length-1] = 1;
            for ( int i = input.length-2 ; i >= 0 ; i-- ) {
                output[i] = output[i+1] * input[i+1];
            }
            int left=1;
            for(int i=0; i<input.length; i++){
                output[i] = output[i] * left;
                left = left * input[i];
            }
            return output;
        }
    }

    @DataProvider(name = "test_prd")
    public Iterator<Object[]> dataProvider() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{new int[]{2, 3, 4, 5}, new int[]{60, 40, 30, 24}});
        data.add(new Object[]{new int[]{2, 3}, new int[]{3, 2}});
        data.add(new Object[]{new int[]{2, 3, 4}, new int[]{12, 8, 6}});
        int len = 30;
        int[] longRange = new int[len];
        for ( int i = 0 ; i < len ; i++ )
            longRange[i] = i+1;
        int[] longRangeExp = {1409286144,-1442840576,469762048,1426063360,1140850688,-1912602624,201326592,-1434451968,-1275068416,570425344,2080374784,-956301312,-1543503872,-2046820352,1811939328,-717225984,335544320,-637534208,-603979776,285212672,67108864,1040187392,-872415232,-478150656,1946157056,-771751936,1006632960,-1023410176,1677721600,-1241513984};
        data.add(new Object[]{longRange, longRangeExp});
/*
        data.add(new Object[]{new int[]{}, new int[]{}});
        data.add(new Object[]{new int[1], new int[]{1}});
        data.add(new Object[]{new int[]{60}, new int[]{1}});
        data.add(new Object[]{null, new int[]{}});
*/
        return data.iterator();
    }

    @Test(dataProvider = "test_prd")
    public void prod_ex_test(int[] input, int[] expected) {
        try {
            long start = System.nanoTime();
            int[] output = prod_ex(input);
            long end = System.nanoTime();
            System.out.println("prod_ex:[" +
                    Arrays.stream(output).mapToObj(String::valueOf).collect(Collectors.joining(",")) +
                    "]:" +
                    (end-start)
            );
            Assert.assertEquals(output, expected);
            // System.out.println("prod_ex_test Passed");
        } finally {
            // System.out.println("prod_ex_test Completed");
        }
    }

    @Test(dataProvider = "test_prd")
    public void prod_ex_1_test(int[] input, int[] expected) {
        try {
            long start = System.nanoTime();
            int[] output = prod_ex_1(input);
            long end = System.nanoTime();
            System.out.println("prod_ex_1:[" +
                    Arrays.stream(output).mapToObj(String::valueOf).collect(Collectors.joining(",")) +
                    "]:" +
                    (end-start)
            );
            Assert.assertEquals(output, expected);
            // System.out.println("prod_ex_1_test Passed");
        } finally {
            // System.out.println("prod_ex_1_test Completed");
        }
    }
}
