package com.mybank.program.data;

import com.mybank.program.models.*;

public class BankData {

    private SavingsAccount[] savingsAccounts;
    private CheckingAccount[] checkingAccounts;
    private IndividualCostumer[] individualCostumers;
    private BusinessCostumer[] businessCostumers;
    private Transaction[] transactions;
    private Bank[] banks;

    public BankData() {



//

    }

    public SavingsAccount[] getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(SavingsAccount[] savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public CheckingAccount[] getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(CheckingAccount[] checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public IndividualCostumer[] getIndividualCostumers() {
        return individualCostumers;
    }

    public void setIndividualCostumers(IndividualCostumer[] individualCostumers) {
        this.individualCostumers = individualCostumers;
    }

    public BusinessCostumer[] getBusinessCostumers() {
        return businessCostumers;
    }

    public void setBusinessCostumers(BusinessCostumer[] businessCostumers) {
        this.businessCostumers = businessCostumers;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public Bank[] getBanks() {
        return banks;
    }

    public void setBanks(Bank[] banks) {
        this.banks = banks;
    }
}
