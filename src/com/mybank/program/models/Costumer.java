package com.mybank.program.models;

import java.time.LocalDate;

public class Costumer {
    private int costumerNr;
    private LocalDate firstRegistration;
    private String id;
    private String name;
    private String surname;
    private String email;
    private String gender;
    private boolean married;
    private String password;

    public Costumer(int costumerNr, LocalDate firstRegistration, String id, String name, String surname, String email, String gender, boolean married, String password) {
        this.costumerNr = costumerNr;
        this.firstRegistration = firstRegistration;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.married = married;
        this.password = password;
    }


    public int getCostumerNr() {
        return costumerNr;
    }

    public void setCostumerNr(int costumerNr) {
        this.costumerNr = costumerNr;
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

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public LocalDate getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(LocalDate firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "costumerNr='" + costumerNr + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", married=" + married +
                '}';
    }

    public String covertToCSVLine() {
        return costumerNr + "," + firstRegistration + "," + id + "," + name + "," + surname + "," + email + "," + gender + "," + married + "," + password;
    }
}
