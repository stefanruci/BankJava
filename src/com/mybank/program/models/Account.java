package com.mybank.program.models;

public class Account {
    private String costumerId;
    private String accNr;
    private String iban;
    private long amount;
    private String currency;
    private Transaction[] transactions;


    public String getAccNr() {
        return accNr;
    }

    public Account(String costumerId, String accNr, String iban, long amount, String currency, Transaction[] transactions) {
        this.costumerId = costumerId;
        this.accNr = accNr;
        this.iban = iban;
        this.amount = amount;
        this.currency = currency;
        this.transactions = transactions;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accNr='" + accNr + '\'' +
                ", iban='" + iban + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
