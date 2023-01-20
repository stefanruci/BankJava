package com.mybank.program.models;

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(int costumerId, String accNr, String iban, double amount, String currency, double interestRate) {
        super(costumerId, accNr, iban, amount, currency);
        this.interestRate = interestRate;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void monthEnd() {
        double interest = getAmount() * interestRate / 12;
        deposit( interest);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "interest=" + interestRate +
                "} " + super.toString();
    }

    @Override
    public String covertToCSVLine() {
        return super.covertToCSVLine() + "," + interestRate;
    }
}
