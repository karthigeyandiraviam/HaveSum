package com.ddk;

public class AddTwoFractions {
    public AddTwoFractions(Integer[] fraction1, Integer[] fraction2) {
        this.fraction1 = fraction1;
        this.fraction2 = fraction2;
        add();
    }

    @Override
    public String toString() {
        return (this.sum[0] % this.sum[1] == 0) ?
                Integer.toString(this.sum[0]/this.sum[1]) :
                this.sum[0] + "/" + this.sum[1];
    }

    int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a%b);
    }

    void add() {
        this.sum = new Integer[2];
        if ( this.fraction1[1] == this.fraction2[1] ) {
            this.sum[0] = this.fraction1[0] + this.fraction2[0];
            this.sum[1] = this.fraction2[0];
        } else {
            this.sum[1] = this.fraction1[1] * this.fraction2[1];
            this.sum[0] = this.fraction1[0] * (this.sum[1] / this.fraction1[1]) +
                    this.fraction2[0] * (this.sum[1] / this.fraction2[1]);
        }
        int g = gcd(this.sum[0], this.sum[1]);
        if ( (this.sum[0] % g == 0) && (this.sum[1] % g == 0) ) {
            this.sum[0] /= g;
            this.sum[1] /= g;
        }
    }


    Integer[] fraction1;
    Integer[] fraction2;
    Integer[] sum;

    public static void main(String[] args) throws Exception {
        String f1 = "2/9";
        String f2 = "7/12";

        String[] f1Split = f1.split("/");
        if ( f1Split.length != 2 )
            throw new Exception("Fraction 1 is not a valid fraction");

        String[] f2Split = f2.split("/");
        if ( f2Split.length != 2 )
            throw new Exception("Fraction 2 is not a valid fraction");

        Integer[] fr1 = new Integer[2];
        fr1[0] = Integer.parseInt(f1Split[0]);
        fr1[1] = Integer.parseInt(f1Split[1]);

        Integer[] fr2 = new Integer[2];
        fr2[0] = Integer.parseInt(f2Split[0]);
        fr2[1] = Integer.parseInt(f2Split[1]);

        System.out.println( new AddTwoFractions(fr1, fr2).toString() );
    }
}
