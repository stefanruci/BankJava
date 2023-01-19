package com.mybank.program.controllers;

import com.mybank.program.data.BankData;
import com.mybank.program.models.*;

import com.mybank.program.data.BankData;
import com.mybank.program.models.*;
import java.util.Scanner;

import static com.mybank.program.models.CostumerType.BUSINESS;

public class BankController {
    private final Scanner scanner = new Scanner(System.in);
    private BankData bankData;
    private IndividualCostumer individualCostumer;
    private BusinessCostumer businessCostumer;
    private CostumerType costumerType;
    private Bank bank;
    private int choise;

    public BankController() {
        bankData = new BankData();
        startProgram();
    }

    public void startProgram() {
        startMenu();
    }

    private void loginMenu() {
        String id, password;

        while (true) {
            System.out.println("Please enter your username:");
            id = scanner.nextLine();

            System.out.println("Please enter your password:");
            password = scanner.nextLine();

            if (verifyCredentials(id, password)) {

                System.out.println("Login successful!");
                initializeCostumerInfo(id);
                if (costumerType == BUSINESS) {
                    dashboardMenu(businessCostumer);
                } else {
                    dashboardMenu(individualCostumer);
                }


                break;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }


        }
    }

    private void initializeCostumerInfo(String id) {
        if (costumerType == BUSINESS) {
            setBusinessCostumer(id, bankData.getBusinessCostumers());
        } else setIndividualCostumer(id, bankData.getIndividualCostumers());
    }

    private boolean verifyCredentials(String id, String password) {
        if (costumerType == BUSINESS) {
            for (int i = 0; i < bankData.getBusinessCostumers().length; i++) {
                if (id == bankData.getBusinessCostumers()[i].getId() && password == bankData.getBusinessCostumers()[i].getPassword()) {
                    return true;
                }

            }
        } else {
            for (int i = 0; i < bankData.getIndividualCostumers().length; i++) {
                if (id == bankData.getIndividualCostumers()[i].getId() && password == bankData.getIndividualCostumers()[i].getPassword()) {
                    return true;
                }

            }

        }
        return false;

    }

    private void startMenu() {
        System.out.println("HELLO TO MyBANK\n" +
                "Press : \n" +
                "1. login as Business \n" +
                "2. login as Individual " +
                "\n0. quit program ");
        choise = scanner.nextInt();

        startMenuHandler();
    }

    private void startMenuHandler() {
        switch (choise) {
            case 1:
                costumerType = BUSINESS;
                loginMenu();

                break;
            case 2:
                costumerType = CostumerType.INDIVIDUAL;
                loginMenu();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                startMenu();
                break;
        }
    }

    private IndividualCostumer getIndividualCostumer() {
        return individualCostumer;
    }

    private BusinessCostumer getBusinessCostumer() {
        return businessCostumer;
    }

    private void setIndividualCostumer(String id, Costumer[] costumers) {
        individualCostumer = (IndividualCostumer) findCostumer(id, costumers);
    }

    private void setBusinessCostumer(String id, Costumer[] costumers) {
        businessCostumer = (BusinessCostumer) findCostumer(id, costumers);

    }

    private Costumer findCostumer(String id, Costumer[] costumers) {
        for (int i = 0; i < costumers.length; i++) {
            if (costumers[i].getId() == id) {
                return costumers[i];
            }
        }
        return null;
    }

    ;

    public void dashboardMenu(Costumer costumer) {
        printCostumerStatus(costumer);
        System.out.println();
    }

    public void reset() {
        bank = null;
        individualCostumer = null;
        businessCostumer = null;
    }

    private void printCostumerStatus(Costumer costumer) {

        System.out.println("\n\t\t Costumer name  " + costumer.getName() + "\n");

        if (costumer.getSavingsAccounts().length > 0) {
            SavingsAccount savingsAccount;
            System.out.println("\n\t\tSaving accounts:  \n");
            for (int i = 0; i < costumer.getSavingsAccounts().length; i++) {
                savingsAccount = costumer.getSavingsAccounts()[i];
                System.out.println("account number " + savingsAccount.getAccNr() + "\t amount : " + savingsAccount.getAmount() + "" +
                        "\t currency" + savingsAccount.getCurrency() + "\n");

            }
        }

        if (costumer.getCheckingAccounts().length > 0) {
            CheckingAccount checkingAccount;
            System.out.println("\n\t\tChecking accounts:  \n");
            for (int i = 0; i < costumer.getCheckingAccounts().length; i++) {
                checkingAccount = costumer.getCheckingAccounts()[i];
                System.out.println("account number " + checkingAccount.getAccNr() + "\t amount : " + checkingAccount.getAmount() + "" +
                        "\t currency" + checkingAccount.getCurrency() + "\n");

            }
        }
    }

}