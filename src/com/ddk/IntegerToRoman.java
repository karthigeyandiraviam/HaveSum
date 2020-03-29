package com.ddk;

public class IntegerToRoman {
    public static String intToRoman(int num) {
        Integer[] integers = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int temp = num;
        String roman = "";
        for ( int i = 0 ; i < integers.length ; i++ ) {
            while ( temp >= integers[i] ) {
                roman += romans[i];
                temp -= integers[i];
            }
        }
        return roman;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(25));
        System.out.println(intToRoman(518));
        System.out.println(intToRoman(1000));
        System.out.println(intToRoman(1994));
        System.out.println(intToRoman(999));
        System.out.println(intToRoman(3));
    }
}
