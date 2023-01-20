package com.mybank.program.models;

public class Bank {
    private String name;
    private String address;
    private String swiftCode;

    public Bank(String name, String address, String swiftCode) {
        this.name = name;
        this.address = address;
        this.swiftCode = swiftCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }


    public String covertToCSVLine() {
        return name + "," + address + "," + swiftCode;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", swiftCode='" + swiftCode + '\'' +
                '}';
    }
}
