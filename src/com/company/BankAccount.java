package com.company;

public class BankAccount {
    private double balance;
    private float interestRate;
    private int accountID;
    private static int nextID = 100;


    public BankAccount(){
        interestRate = 0.02f;
        accountID = nextID;
        nextID++; //Same as nextID = nextID + 1
    }
    public BankAccount(double intialBalance, float initialRate){
        balance = intialBalance;
        interestRate = initialRate;
        accountID = nextID;
        nextID += 1;
    }

    public int getAccountID() {
        return accountID;
    }
    public void deposit(double amount){
        balance += amount;
    }
    public boolean withdraw(double amount){
        if (amount > balance){
            return false;
        }
        balance = balance - amount;
        return true;
    }
    public double checkBalance() {
        return balance;
    }
    public double addInterest(){
        balance += interestRate * balance;
        return balance;
    }
}
