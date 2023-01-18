package com.mybank.program.models;

public class CheckingAccount extends Account{

    public double maintanceFee;

    public CheckingAccount(String accNr, String iban, long amount, double maintanceFee) {
        super(costumerId, accNr, iban, amount, currency, transactions);
        this.maintanceFee = maintanceFee;
    }

    public double getMaintenanceFee() {
        return maintanceFee;
    }

    public void setMaintenanceFee(double maintenanceFee) {
        this.maintanceFee = maintenanceFee;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "maintanceFee=" + maintanceFee +
                "} " + super.toString();
    }
}
