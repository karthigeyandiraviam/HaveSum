package com.ddk.common;

public class Demo implements java.io.Serializable {
    public Demo() {}

    public String name;
    public Integer age;
    public Address address;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if ( name != null ) sb.append("Name: ").append(name);
        if ( age != null ) sb.append("\nAge: ").append(age);
        if ( address != null ) sb.append("\n").append(address.toString());
        return sb.toString();
    }
}
