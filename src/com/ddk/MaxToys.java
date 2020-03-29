package com.ddk;
import javafx.collections.transformation.SortedList;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

class Toys {
    List<Integer> toyList;
    Integer total;

    public Toys() {
        toyList = new ArrayList<>();
        total = 0;
    }

    public Toys(int price) {
        toyList = new ArrayList<>();
        toyList.add(price);
        total = price;
    }

    public List<Integer> getToyList() {
        return toyList;
    }

    public int getNumberOfToys() {
        return getToyList().size();
    }

    public int getTotal() {
        return total;
    }

    public void addTotal(int price, int totalCost ) {
        if ( (price <= totalCost) && (total + price) <= totalCost ) {
            total += price;
            toyList.add(price);
        }
    }

    public void printData() {
        getToyList().stream().forEach( e -> System.out.print(e + " + "));
        System.out.println(" = " + getTotal());
        System.out.println(getNumberOfToys() + "\n================");
    }
}

public class MaxToys {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        List<Integer> toyPrices = Arrays.stream(prices).sorted().boxed().collect(Collectors.toList());

        int count = 0;
        for ( Integer p : toyPrices ) {
            if ( p <= k ) {
                k -= p;
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        System.out.println(maximumToys(prices, k));
        scanner.close();
    }
}
