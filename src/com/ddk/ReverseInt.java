package com.ddk;

public class ReverseInt {
    public static int reverse(int x) {
        if ( x == 0 )
            return x;

        boolean isNeg = (x < 0)?true:false;
        x = Math.abs(x);

        int rev = 0;
        while ( x > 0 ) {
            if (rev > Integer.MAX_VALUE/10)
                return 0;
            rev = rev * 10 + x % 10;
            x /= 10;
            System.out.println("rev=" + rev + ", x=" + x);
        }

        if ( isNeg )
            return rev * -1;
        return rev;
    }

    public static void main(String[] arg) {
        System.out.println(reverse(1534236469));
    }
}
