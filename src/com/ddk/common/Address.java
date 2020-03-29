package com.ddk.common;

public class Address implements java.io.Serializable {
    public Address() {}
    public String address1;
    public String address2;
    public String city;
    public String state;
    public Integer zip;
    public String country;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if ( address1 != null ) sb.append("Address 1: ").append(address1);
        if ( address2 != null ) sb.append("\nAddress 2: ").append(address2);
        if ( city != null ) sb.append("\nCity: ").append(city);
        if ( state != null ) sb.append("\nState: ").append(state);
        if ( zip != null ) sb.append("\nZip: ").append(zip);
        if ( country != null ) sb.append("\nCountry: ").append(country);
        return sb.toString();
    }
}
