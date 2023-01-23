package com.mybank.program;

import com.mybank.program.ui.BankUI;

/**
 * REQUIREMENTS :
 * Suppose that a bank offers customers the following account types:
 * • A savings account that earns interest. The interest compounds monthly and is computed on the minimum monthly balance.
 * • A checking account that has no interest, gives you three free withdrawals per month, and charges a $1 transaction fee for each additional withdrawal.
 * Design and implement a program that will manage a set of accounts of both types. It should be structured so that other account types can be added without affecting the main processing loop.
 * Supply a menu :
 * D)eposit
 * W)ithdraw
 * M)onth end
 * S)ave
 * L)oad
 * Q)uit.
 * For deposits and withdrawals, query the account number and amount.
 * Print the balance of the account after each transaction.
 * In the “Month end” command, accumulate interest or clear the transaction counter, depending on the type of the bank account.
 * Then print the balance of all accounts.
 * The program should be apple to save the data to a text file and
 * load them to the program when the user chooses the appropriate command from the menu.
 */

public class BankApplication {
    public static void main(String[] args) {

        BankUI bankUI = new BankUI();
        bankUI.startProgram();

    }
}
/**
The program starts with the main method.
The main method creates a new instance of the 
BankUI class and calls the startProgram method on it.
 */