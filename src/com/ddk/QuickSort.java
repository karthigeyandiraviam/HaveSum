package com.ddk;

import java.util.Arrays;

public class QuickSort {
    void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    int partition(int a[], int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for ( int j = low ; j < high ; j++ ) {
            if ( a[j] <= pivot ) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[i+1];
        a[i+1] = a[high];
        a[high] = temp;

        return i+1;
    }

    void quickSort(int a[], int low, int high) {
        if ( low < high ) {
            int pi = partition(a, low, high);
            quickSort(a, low, pi-1);
            quickSort(a, pi+1, high);
        }
    }

    public static void main(String[] args) {
        // int[] A = {10, 7, 8, 9, 1, 5};
        int[] A = {4, 3, 7, 5, 8, 6, 10};
        // int[] A = {10, 8, 7, 6, 5, 3, 1};
        QuickSort qs = new QuickSort();
        qs.quickSort(A, 0, A.length-1);
        qs.printArray(A);
        System.out.println();
    }
}
