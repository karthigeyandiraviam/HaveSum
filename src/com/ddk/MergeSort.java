package com.ddk;

import java.util.Arrays;

public class MergeSort {
    static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + " "));
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

    public static void main(String[] args) {
        // int[] A = {10, 6, 8, 5, 7, 3, 4};
        int[] A = {4, 3, 7, 5, 8, 6, 10};
        mergeSort(A, A.length);
        printArray(A);
    }
}
