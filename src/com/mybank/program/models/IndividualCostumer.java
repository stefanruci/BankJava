package com.mybank.program.models;

public class IndividualCostumer  extends Costumer{

    private String subscriptionNr;

    public IndividualCostumer(String id, String name, String surname, String gender, String subscriptionNr, String password) {
        super(id, name, surname, gender, password);
        this.subscriptionNr = subscriptionNr;
    }

    public String getSubscriptionNr() {
        return subscriptionNr;
    }

    public void setSubscriptionNr(String subscriptionNr) {
        this.subscriptionNr = subscriptionNr;
    }

    @Override
    public String toString() {
        return "IndividualCostumer{" +
                "subscriptionNr='" + subscriptionNr + '\'' +
                "} " + super.toString();
    }
}
