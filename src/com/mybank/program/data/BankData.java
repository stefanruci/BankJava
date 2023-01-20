package com.mybank.program.data;

import com.mybank.program.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankData {
    public BankData() {
        reloadData();
    }

    private ArrayList<SavingsAccount> savingsAccounts;
    private ArrayList<CheckingAccount> checkingAccounts;
    private ArrayList<Costumer> costumers;
    private ArrayList<Transaction> transactions;
    private ArrayList<Bank> banks;


    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public ArrayList<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(ArrayList<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public ArrayList<CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(ArrayList<CheckingAccount> checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public ArrayList<Costumer> getCostumers() {
        return costumers;
    }

    public void setCostumers(ArrayList<Costumer> costumers) {
        this.costumers = costumers;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


    public int checkMaxTransNr() {
        int max = -1;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionNr() > max) {
                max = transaction.getTransactionNr();
            }
        }
        return max;
    }

    public List<String> readFileLines(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBanksFromFile() {
        List<String> strings = readFileLines("src/resources/banks.csv");
        banks = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");
            if (split.length == 3) {
                banks.add(new Bank(split[0], split[1], split[2]));
            }
        }

    }

    private void setSavingsAccountsFromFile() {
        List<String> strings = readFileLines("src/resources/savingsAccounts.csv");
        savingsAccounts = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");
            if (split.length == 6) {
                savingsAccounts.add(new SavingsAccount(
                        Integer.parseInt(split[0])
                        , split[1]
                        , split[2]
                        , Double.parseDouble(split[3].trim())
                        , split[4]
                        , Double.parseDouble(split[5])));
            }
        }
    }

    private void setCheckingAccountsFromFile() {
        List<String> strings = readFileLines("src/resources/checkingAccounts.csv");
        checkingAccounts = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");
            if (split.length == 6) {

                checkingAccounts.add(new CheckingAccount(
                        Integer.parseInt(split[0].trim())
                        , split[1]
                        , split[2]
                        , Double.parseDouble(split[3])
                        , split[4]
                        , Double.parseDouble(split[5])));
            }
        }


    }

    private void setCostumersFromFile() {
        List<String> strings = readFileLines("src/resources/costumers.csv");
        costumers = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");
            if (split.length == 9) {

                costumers.add(new Costumer(
                        Integer.parseInt(split[0].trim()),
                        LocalDate.parse(split[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        , split[2]
                        , split[3]
                        , split[4]
                        , split[5]
                        , split[6]
                        , Boolean.parseBoolean(split[7])
                        , split[8]
                ));
            }
        }

    }

    private void setTransactionsFromFile() {
        ArrayList<String> strings = (ArrayList<String>) readFileLines("src/resources/transactions.csv");
        transactions = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");
            if (split.length == 6) {
                transactions.add(new Transaction(
                        Integer.parseInt(split[0]),
                        split[1],
                        split[2],
                        LocalDateTime.parse(split[3], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                        TransactionType.valueOf(split[4].toUpperCase(Locale.ROOT)),
                        Double.parseDouble(split[5])
                ));
            }
        }
    }

    public void saveAllInFiles() {

        try (FileWriter writer = new FileWriter("src/resources/costumers.csv")) {

            for (Bank bank : banks) {
                writer.write(bank.covertToCSVLine() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter("src/resources/costumers.csv")) {

            for (Costumer costumer : costumers) {
                writer.write(costumer.covertToCSVLine() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (FileWriter writer = new FileWriter("src/resources/checkingAccounts.csv")) {

            for (CheckingAccount checkingAccount : checkingAccounts) {
                writer.write(checkingAccount.covertToCSVLine() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter("src/resources/savingsAccounts.csv")) {

            for (SavingsAccount savingsAccount : savingsAccounts) {
                writer.write(savingsAccount.covertToCSVLine() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (FileWriter writer = new FileWriter("src/resources/transactions.csv")) {

            for (Transaction transaction : transactions) {
                writer.write(transaction.covertToCSVLine() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void reloadData() {
        setBanksFromFile();
        setSavingsAccountsFromFile();
        setCheckingAccountsFromFile();
        setCostumersFromFile();
        setTransactionsFromFile();
        System.out.println("Data successfully loaded");
    }

}
