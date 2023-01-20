package com.mybank.program.models;

public class Account {
    private int costumerNr;
    private String accNr;
    private String iban;
    private double amount;
    private String currency;


    public Account(int costumerNr, String accNr, String iban, double amount, String currency) {
        this.costumerNr = costumerNr;
        this.accNr = accNr;
        this.iban = iban;
        this.amount = amount;
        this.currency = currency;
    }

    public String getAccNr() {
        return accNr;
    }

    public void setAccNr(String accNr) {
        this.accNr = accNr;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCostumerNr() {
        return costumerNr;
    }

    public void setCostumerNr(int costumerNr) {
        this.costumerNr = costumerNr;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void deposit(double sum) {
        this.amount += sum;
    }

    public void withdraw(double sum) {
        this.amount -= sum;
    }


    public String covertToCSVLine() {
        return costumerNr + "," + accNr + "," + iban + "," + amount + "," + currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "costumerNr='" + costumerNr + '\'' +
                ", accNr='" + accNr + '\'' +
                ", iban='" + iban + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
