package com.ddk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindMissingElement {
    static int solution(int[] A) {
        if ( A.length <= 0 )
            return 1;
        Arrays.sort(A);
        int smallest = 1;
        while ( smallest <= A[A.length-1] ) {
            int idx = Arrays.binarySearch(A, smallest);
            if ( idx < 0 )
                break;
            smallest++;
        }
        return smallest;
    }

    static boolean findElement(int[] A, int key) {
        for ( int i = 0 ; i < A.length ; i++ ) {
            if ( A[i] == key )
                return true;
        }
        return false;
    }

    static int findLargest(int[] A) {
        int largest = -1;
        for ( int i = 0 ; i < A.length ; i++ ) {
            if ( A[i] > largest )
                largest = A[i];
        }
        return largest;
    }

    static int solution1(int[] A) {
        if ( A.length <= 0 )
            return 1;
        int smallest = 1;
        int largest = findLargest(A);
        while ( smallest <= largest ) {
            if ( ! findElement(A, smallest) )
                break;
            smallest++;
        }
        return smallest;
    }

    static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    static int solution2(int[] A) {
        if ( A.length <= 0 )
            return 1;
        mergeSort(A, A.length);
        int smallest = 1;
        while ( smallest <= A[A.length-1] ) {
            int idx = Arrays.binarySearch(A, smallest);
            if ( idx < 0 )
                break;
            smallest++;
        }
        return smallest;
    }
    public static void main(String[] args) {
        int[] A = {8,7,6,5,4,3,2,1};
        int[] copyA = Arrays.copyOf(A, A.length);
        long start = System.nanoTime();
        int ret = solution(copyA);
        long end = System.nanoTime();
        System.out.println("Missing element: " + ret + ", solution with Arrays.sort took " + (end-start));

        copyA = Arrays.copyOf(A, A.length);
        start = System.nanoTime();
        ret = solution1(copyA);
        end = System.nanoTime();
        System.out.println("Missing element: " + ret + ", solution1 with search unsorted took " + (end-start));

        copyA = Arrays.copyOf(A, A.length);
        start = System.nanoTime();
        ret = solution2(copyA);
        end = System.nanoTime();
        System.out.println("Missing element: " + ret + ", solution2 with merge sort took " + (end-start));
    }
}
