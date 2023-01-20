package com.mybank.program.models;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionNr;
    private String costumerId;
    private String accountNr;
    private LocalDateTime time;
    private TransactionType transactionType;
    private double amount;

    public Transaction(int transactionNr, String costumerId, String accountNr, LocalDateTime time, TransactionType transactionType, double amount) {
        this.transactionNr = transactionNr;
        this.costumerId = costumerId;
        this.accountNr = accountNr;
        this.time = time;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public int getTransactionNr() {
        return transactionNr;
    }

    public void setTransactionNr(int transactionNr) {
        this.transactionNr = transactionNr;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionNr=" + transactionNr +
                ", costumerId='" + costumerId + '\'' +
                ", accountNr='" + accountNr + '\'' +
                ", time=" + time +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }

    public String covertToCSVLine() {
        return transactionNr + "," + costumerId + "," + accountNr + "," + time + "," + transactionType + "," + amount;
    }
}
