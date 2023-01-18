package com.mybank.program.models;

public class SavingsAccount extends Account{

    private double interest;

    public SavingsAccount(String accNr, String iban, long amount, double interest, String costumerId, Transaction[] transactions, String currency) {
        super(costumerId, accNr, iban, amount, currency, transactions);
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }


    @Override
    public String toString() {
        return "SavingsAccount{" +
                "interest=" + interest +
                "} " + super.toString();
    }
}
