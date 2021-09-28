package com.company;

import java.util.ArrayList;
import java.util.Optional;

public class Customer {
    private int customerID;
    private String name;
    private ArrayList<BankAccount> accounts;

    public Customer(String customerName, int taxID) {
        customerID = taxID;
        name = customerName;
        accounts = new ArrayList<BankAccount>();
    }

    public BankAccount openAccount(double initialDeposit) {
        var newAccount = new BankAccount();
        newAccount.deposit(initialDeposit);
        var didSucceed = accounts.add(newAccount);
        return newAccount;
    }

    public Optional<BankAccount> closeAccount(int accountNumber){
        for (var account: accounts) {
            if (account.getAccountID() == accountNumber) {
                System.out.println("Removing account with Account ID " + accountNumber + " from Customer " + name);
                accounts.remove(account);
                return Optional.of(account);
            }
        }
        System.out.println("Account with account number " + customerID + " is not " + name + "'s account");
        return Optional.empty();
    }

    public int getID() {return customerID;}

    public String getName() {return name;}

}
