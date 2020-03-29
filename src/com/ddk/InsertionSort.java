package com.ddk;

import java.util.Arrays;

public class InsertionSort {

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach( a -> System.out.print(a + " "));
        System.out.println();
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

    public static void insSort(int[] array) {
        for ( int j = 1 ; j < array.length ; j++ ) {
            int key = array[j];
            int i = j - 1;
            while ( i >= 0 && array[i] > key ) {
                array[i+1] = array[i];
                i--;
            }
            array[i+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {30, 29, 28, 27, 26, 21, 22, 23, 24, 25, 20, 19, 18, 17, 16, 11, 12, 13, 14, 15, 10, 9, 8, 7, 6, 1, 2, 3, 4, 5};
        // Insertion Sort
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        printArray(arrayCopy);
        long start = System.nanoTime();
        insSort(arrayCopy);
        double elapsed = (System.nanoTime() - start) / 1e6;
        System.out.println("Elapsed " + elapsed);
        printArray(arrayCopy);

        // Merge Sort
        arrayCopy = Arrays.copyOf(array, array.length);
        printArray(arrayCopy);
        start = System.nanoTime();
        mergeSort(arrayCopy, array.length);
        elapsed = (System.nanoTime() - start) / 1e6;
        System.out.println("Elapsed " + elapsed);
        printArray(arrayCopy);
    }
}
