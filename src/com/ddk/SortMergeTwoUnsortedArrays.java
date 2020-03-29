package com.ddk;

import java.util.Arrays;

public class SortMergeTwoUnsortedArrays {
    public static int[] sortAndMerge(int n[], int m[]) {
        int[] res = new int[n.length + m.length];
        Arrays.sort(n);
        Arrays.sort(m);
        int i = 0, j = 0, k = 0;
        while ( i < n.length && j < m.length ) {
            if ( n[i] < m[j] )
                res[k++] = n[i++];
            else
                res[k++] = m[j++];
        }
        while ( i < n.length )
            res[k++] = n[i++];
        while ( j < m.length )
            res[k++] = m[j++];
        return res;
    }

    public static int[] mergeAndSort(int n[], int m[]) {
        int[] res = new int[n.length + m.length];
        int i = 0, j = 0, k = 0;
        while ( i < n.length )
            res[k++] = n[i++];
        while ( j < m.length )
            res[k++] = m[j++];
        Arrays.sort(res);
        return res;
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        int[] res1 = sortAndMerge(new int[]{10,5,15,11,9},new int[]{5,3,2,1});
        long end = System.nanoTime();
        Arrays.stream(res1).forEach(a -> System.out.print(a + " "));
        System.out.println("\nsortAndMerge took " + (end-start));

        start = System.nanoTime();
        int[] res2 = mergeAndSort(new int[]{10,5,15,11,9},new int[]{5,3,2,1});
        end = System.nanoTime();
        Arrays.stream(res2).forEach(a -> System.out.print(a + " "));
        System.out.println("\nmergeAndSort took " + (end-start));
    }
}
