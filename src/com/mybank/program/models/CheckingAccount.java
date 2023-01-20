package com.mybank.program.models;

public class CheckingAccount extends Account{

    private double maintenanceFee;

    public CheckingAccount(int costumerId, String accNr, String iban, double amount, String currency, double maintenanceFee) {
        super(costumerId, accNr, iban, amount, currency);
        this.maintenanceFee = maintenanceFee;
    }

    public double getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(double maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }


    @Override
    public String covertToCSVLine() {
        return super.covertToCSVLine() + "," + maintenanceFee;
    }
    @Override
    public String toString() {
        return "CheckingAccount{" +
                "maintenanceFee=" + maintenanceFee +
                "} " + super.toString();
    }
}
