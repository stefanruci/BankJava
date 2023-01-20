package com.mybank.program.ui;

import com.mybank.program.data.BankData;
import com.mybank.program.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;


public class BankUI {
    private final Scanner input = new Scanner(System.in);
    private BankData bankData;
    private Costumer costumer;
    private Bank bank;
    private int choice;

    public BankUI() {

    }

    public void startProgram() {
        bankData = new BankData();
        bank = bankData.getBanks().get(0);
        System.out.println("HELLO TO    \"" + bank.getName() + "\"");
        startMenu();
    }

    private void startMenu() {

        System.out.println(
                "\nPress : \n"
                        + "1. Login \n"
                        + "0. Quit program ");
        choice = Integer.parseInt(input.nextLine());
        startMenuHandler();
    }

    private void startMenuHandler() {
        switch (choice) {
            case 1:
                loginMenu();

                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("not valid input !");
                startMenu();
                break;
        }
    }

    private void loginMenu() {
        System.out.println("Please enter your client number:");
        int clientNr = Integer.parseInt(input.nextLine());
        System.out.println("Please enter your password:");
        String password = input.nextLine();

        if (verifyCredentials(clientNr, password)) {
            System.out.println("Login successful!");
            setCostumerInfo(clientNr);
            costumerDashboardMenu();
        } else {
            System.out.println("Invalid user number or password. Please try again.");
            loginMenu();
        }


    }


    private void costumerDashboardMenu() {
        System.out.println("\n\t\t Hello Ms/Mrs  " + costumer.getName() + "\n");
        printCostumerStatus();
        System.out.println("Press : \n" +
                "D)eposit \n" +
                "W)ithdraw \n" +
                "M)onth-End \n" +
                "S)ave \n" +
                "L)oad \n" +
                "Q)uit\n");
        char c = input.nextLine().toLowerCase().charAt(0);
        costumerDashboardMenuHandler(c);
    }

    public void costumerDashboardMenuHandler(char c) {
        switch (c) {
            case 'd':
                depositMenu();
                break;
            case 'w':
                withdrawMenu();
                saveAll();
                costumerDashboardMenu();
                break;
            case 'm':
                monthEndOperation();
                saveAll();
                costumerDashboardMenu();
                break;
            case 'l':
            case 's':
                saveAll();
                costumerDashboardMenu();
                break;
            case 'q':
                bankData.saveAllInFiles();
                reset();
                startProgram();
                break;
            default:
                System.out.println("this is not a valid character !");
                costumerDashboardMenu();
                break;
        }
    }

    private void saveAll() {

        bankData.saveAllInFiles();
        System.out.println("Data successfully saved");
        bankData.reloadData();
    }

    private void monthEndOperation() {
        System.out.println("Enter accountNumber : ");
        String accNr = input.nextLine();
        monthEndOperation(accNr);
    }


    private void monthEndOperation(String accNumber) {
        double amount;
        double interestRate;
        SavingsAccount savingAcc = findSavingAcc(accNumber);
        double interestIncome;
        amount = savingAcc.getAmount();
        interestRate = savingAcc.getInterestRate();

        if (monthlyEndHaveNotDone(accNumber)) {
            interestIncome = amount * (interestRate / 100 / 12);
            savingAcc.deposit(interestIncome);
            bankData.getTransactions().add(
                    new Transaction(
                            findNextTransNumber(),
                            costumer.getId(),
                            savingAcc.getAccNr(),
                            LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            TransactionType.INTEREST_INCOME,
                            interestIncome
                    )
            );
            System.out.println("Transaction successfully done");

        } else System.out.println("Interest of this month has already deposit in your account");
    }


    private void withdrawMenu() {
        System.out.println("Press one number where you want to withdraw :  \n"
                + "1. Saving account \n"
                + "2. checking account");
        choice = Integer.parseInt(input.nextLine());
        System.out.println("Enter accountNumber : ");
        String accNr = input.nextLine();
        System.out.println("Enter amount  : ");
        double amountSum = Double.parseDouble(input.nextLine());

        int numberOfWithdrawsThisMonth = getNumberOfWithdrawsThisMonth(accNr);

        if (choice == 1) {
            SavingsAccount savingsAccount = null;
            try {
                savingsAccount = bankData.getSavingsAccounts()
                        .stream()
                        .filter(el -> Objects.equals(el.getAccNr(), accNr))
                        .collect(Collectors.toList())
                        .get(0);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("this is not a saving account");
                depositMenu();
            }


            if (savingsAccount.getAmount() >= amountSum + 15) {

                savingsAccount.withdraw(amountSum);
                bankData.getTransactions().add(
                        new Transaction(
                                findNextTransNumber(),
                                costumer.getId(),
                                accNr,
                                LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                                TransactionType.WITHDRAW,
                                amountSum
                        ));
                System.out.println("Transaction successfully done");
            } else {
                System.out.println("Is not sufficient  balance to perform this operation");
                costumerDashboardMenu();
            }
        } else if (choice == 2) {

            CheckingAccount checkingAccount = null;
            try {
                checkingAccount = findCheckingAcc(accNr);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("this is not a checking account");
                depositMenu();
            }
            if (checkingAccount.getAmount() >= amountSum + 1) {
                if (numberOfWithdrawsThisMonth > 3) {
                    checkingAccount.withdraw(1L);
                    checkingAccount.withdraw(amountSum);
                    bankData.getTransactions().add(
                            new Transaction(
                                    findNextTransNumber(),
                                    costumer.getId(),
                                    accNr,
                                    LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                                    TransactionType.WITHDRAW,
                                    amountSum
                            ));
                    bankData.getTransactions().add(
                            new Transaction(
                                    findNextTransNumber(),
                                    costumer.getId(),
                                    accNr,
                                    LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                                    TransactionType.WITHDRAW_FEE,
                                    1
                            ));
                    System.out.println("Transaction successfully done");
                    costumerDashboardMenu();

                } else {
                    checkingAccount.withdraw(amountSum);
                    bankData.getTransactions().add(
                            new Transaction(
                                    findNextTransNumber(),
                                    costumer.getId(),
                                    accNr,
                                    LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                                    TransactionType.WITHDRAW,
                                    amountSum
                            ));
                    System.out.println("Transaction successfully done");
                    costumerDashboardMenu();
                }

            } else {
                System.out.println("Is not sufficient  balance to perform this operation");
                costumerDashboardMenu();
            }
        } else {
            System.out.println("the account type you  choose is not valid");
            depositMenu();
        }
    }

    private void depositMenu() {

        Scanner input = new Scanner(System.in);
        System.out.println("Press one number where you want to deposit : \n1. Saving account \n2. checking account");
        choice = Integer.parseInt(input.nextLine());
        System.out.println("Enter accountNumber : ");
        String accNr = input.nextLine();
        System.out.println("Enter amount  : ");
        double amountSum = Double.parseDouble(input.nextLine());


        if (amountSum <= 0) {
            System.out.println("Not valid amount");
            depositMenu();
        }

        if (choice == 1) {
            SavingsAccount savingsAccount = null;
            try {
                savingsAccount = bankData.getSavingsAccounts()
                        .stream()
                        .filter(el -> Objects.equals(el.getAccNr().trim(), accNr))
                        .collect(Collectors.toList())
                        .get(0);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("this is not a saving account");
                depositMenu();
            }


            assert savingsAccount != null;
            savingsAccount.deposit(amountSum);
            bankData.getTransactions().add(
                    new Transaction(
                            findNextTransNumber(),
                            costumer.getId(),
                            accNr,
                            LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            TransactionType.DEPOSIT,
                            amountSum
                    )
            );
            System.out.println("Transaction successfully done");
            saveAll();
            costumerDashboardMenu();
        } else if (choice == 2) {
            CheckingAccount checkingAccount = null;
            try {
                checkingAccount = bankData.getCheckingAccounts()
                        .stream()
                        .filter(el -> Objects.equals(el.getAccNr(), accNr))
                        .collect(Collectors.toList())
                        .get(0);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("this is not a checking account");
                depositMenu();
            }

            checkingAccount.deposit(amountSum);

            bankData.getTransactions().add(
                    new Transaction(
                            findNextTransNumber(),
                            costumer.getId(),
                            accNr,
                            LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                            TransactionType.DEPOSIT,
                            amountSum
                    ));
            System.out.println("Transaction successfully done");
            costumerDashboardMenu();

        } else {
            System.out.println("the account type you  choose is not valid");
            depositMenu();
        }
    }

    private void printCostumerStatus() {
        SavingsAccount savingsAccount;
        System.out.println("\nSaving accounts:  \n");
        for (int i = 0; i < bankData.getSavingsAccounts().size(); i++) {
            if (costumer.getCostumerNr() == bankData.getSavingsAccounts().get(i).getCostumerNr()) {
                savingsAccount = bankData.getSavingsAccounts().get(i);
                System.out.println(" Account number " + savingsAccount.getAccNr() + "\t Amount : " + savingsAccount.getAmount() + "" +
                        "\t currency : " + savingsAccount.getCurrency() + "\t Interest Rate  : " + savingsAccount.getInterestRate() + "\n");
            }
        }

        CheckingAccount checkingAccount;
        System.out.println("\nChecking accounts:  \n");
        for (int i = 0; i < bankData.getCheckingAccounts().size(); i++) {
            if (costumer.getCostumerNr() == bankData.getCheckingAccounts().get(i).getCostumerNr()) {
                checkingAccount = bankData.getCheckingAccounts().get(i);
                System.out.println("account number " + checkingAccount.getAccNr() + "\t amount : " + checkingAccount.getAmount() + "" +
                        "\t currency : " + checkingAccount.getCurrency() + "\n");

            }
        }

    }

    private void setCostumerInfo(int costumerNr) {
        Costumer costumer = findCostumer(costumerNr);
        if (costumer != null) {
            this.costumer = costumer;
        }

    }

    private boolean verifyCredentials(int number, String password) {
        Costumer costumer1 = null;
        try {
            costumer1 = findCostumer(number);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return false;
        }

        if (Objects.equals(costumer1.getPassword(), password)) {
            return true;
        }
        return false;
    }


    private Costumer findCostumer(int number) {
        ArrayList<Costumer> collect = bankData.getCostumers();
        collect
                .stream()
                .filter(el -> el.getCostumerNr() == number)
                .collect(Collectors.toList())
        ;
        if (collect.isEmpty()) {
            return null;
        }
        return collect.get(0);
    }

    private SavingsAccount findSavingAcc(String accNumber) {
        List<SavingsAccount> collect = bankData.getSavingsAccounts().stream()
                .filter(acc -> Objects.equals(acc.getAccNr(), accNumber))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            return null;
        }
        return collect.get(0);
    }

    private CheckingAccount findCheckingAcc(String accNumber) {
        List<CheckingAccount> collect = bankData.getCheckingAccounts().stream()
                .filter(acc -> Objects.equals(acc.getAccNr(), accNumber))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            return null;
        }
        return collect.get(0);
    }

    private boolean monthlyEndHaveNotDone(String accNumber) {
        if (!bankData.getTransactions().isEmpty()) {
            for (int i = 0; i < bankData.getTransactions().size(); i++) {
                if (bankData.getTransactions().get(i).getTransactionType() == TransactionType.INTEREST_INCOME
                        && Objects.equals(bankData.getTransactions().get(i).getAccountNr(), accNumber)
                        && bankData.getTransactions().get(i).getTime().getMonth() == LocalDateTime.now().getMonth()) {
                    return false;
                }
            }
        }
        return true;

    }

    private int getNumberOfWithdrawsThisMonth(String accNumber) {
        return (int) bankData.getTransactions().stream()
                .filter(transaction -> Objects.equals(transaction.getAccountNr(), accNumber))
                .count();
    }

    private int findNextTransNumber() {
        int initTransNr = 1000;
        if (bankData.getTransactions().size() != 0) {
            for (int i = 0; i < bankData.getTransactions().size(); i++) {
                if (bankData.getTransactions().get(i).getTransactionNr() >= initTransNr) {
                    initTransNr = bankData.getTransactions().get(i).getTransactionNr();
                }
            }
            initTransNr++;
        }
        return initTransNr;
    }

    public void reset() {
        bank = null;
        costumer = null;
        choice = -1;
        bankData = new BankData();

    }
}