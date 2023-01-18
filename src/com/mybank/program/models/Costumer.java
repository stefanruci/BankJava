package com.mybank.program.models;

public class Costumer  {
    private String id;
    private String name;
    private String surname;
    private String gender;
    private SavingsAccount[] savingsAccounts;
    private CheckingAccount[] checkingAccounts;
    private String password;

    public Costumer(String id, String name, String surname, String gender, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SavingsAccount[] getSavingsAccounts() {
        return savingsAccounts;
    }

    public CheckingAccount[] getCheckingAccounts() {
        return checkingAccounts;
    }
}
