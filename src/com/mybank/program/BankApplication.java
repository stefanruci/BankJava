package com.mybank.program;

import com.mybank.program.controllers.BankController;

public class BankApplication {
    public static void main(String[] args) {

        BankController bankController = new BankController();
        bankController.startProgram();
    }
}