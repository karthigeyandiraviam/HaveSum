package com.ddk;

import java.util.Arrays;

public class QuickSort1 {
    void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    void sort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }

    public static void main(String[] args) {
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        QuickSort1 ob = new QuickSort1();
        ob.sort(arr, 0, n-1);

        System.out.println("sorted array");
        ob.printArray(arr);
    }
}
