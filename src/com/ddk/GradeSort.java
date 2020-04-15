package com.ddk;

import java.util.*;


public class GradeSort implements java.io.Serializable {
    private Map<String, Integer> gradeOrder;
    protected List<String> unsorted_grades;
    protected List<String> sorted_grades;

    public GradeSort() {
        init();
        this.unsorted_grades = new ArrayList<>();
        sortGrades();
    }

    public GradeSort(List<String> unsorted_grades) {
        init();
        this.unsorted_grades = unsorted_grades;
        sortGrades();
    }

    private void init() {
        gradeOrder = new HashMap<String, Integer>() {{
            put("A+", 1);
            put("A", 2);
            put("A-", 3);
            put("B+", 4);
            put("B", 5);
            put("B-", 6);
            put("C+", 7);
            put("C", 8);
            put("C-", 9);
            put("D+", 10);
            put("D", 11);
            put("D-", 12);
            put("F", 13);
        }};
    }

    public void printArray(String[] array) {
        for ( String a : array )
            System.out.print(a + " ");
        System.out.println();
    }

    public void printArray(List<String> array) {
        for ( String a : array )
            System.out.print(a + " ");
        System.out.println();
    }

    public void mergeSort(String[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        String[] l = new String[mid];
        String[] r = new String[n - mid];

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

    public void merge(String[] a, String[] l, String[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (gradeOrder.get(l[i]) <= gradeOrder.get(r[j])) {
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

    public List<String> getUnsorted_grades() {
        return this.unsorted_grades;
    }

    public List<String> getSorted_grades() {
        return this.sorted_grades;
    }

    private void sortGrades() {
        List<String> validGrades = new ArrayList<>();
        for ( String s : unsorted_grades ) {
            if ( gradeOrder.containsKey(s) )
                validGrades.add(s);
        }
        // System.out.println("Insertion sort");
        String[] validGradeArray = validGrades.stream().toArray(String[]::new);

        System.out.println("Merge sort");
        mergeSort(validGradeArray, validGradeArray.length);
        //printArray(validGradeArray);
        sorted_grades = Arrays.asList(validGradeArray);
    }

    public static void main(String[] args) {
        GradeSort gs = new GradeSort(new ArrayList<String>() {{
            add("A");add("A-");add("A+");
        }});
        gs.printArray(gs.getSorted_grades());

        gs = new GradeSort(new ArrayList<String>() {{
            add("A+");add("A-");add("A");
            add("B+");add("B-");add("B+");
            add("C+");add("C-");add("C+");
            add("D");add("D+");add("D-");
            add("E");add("E-");add("E+");
            add("F+");add("F-");add("F");
            add("A+");add("A-");add("A");
            add("B+");add("B-");add("B+");
        }});
        gs.printArray(gs.getSorted_grades());
    }
}
