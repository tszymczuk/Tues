package com.company;

public class Main {

    public static void main(String[] args) {
        var myAccount = new BankAccount();
        var yourAccount = new BankAccount(2000, 0.05f);
        myAccount.deposit(1000);
        var newBalance = myAccount.addInterest();
        var yourBalance = yourAccount.addInterest();
        System.out.println("Your account with account ID " + yourAccount.getAccountID() + " has $" + yourBalance +  " adding interest.");
        var succeeded = myAccount.withdraw(2000);
        if (succeeded)
            System.out.println("You managed to withdraw successfully ");
        else
            System.out.println("You tried to withdraw too much, your balance is $" + myAccount.checkBalance() + " for account ID " + myAccount.getAccountID());
    }
}
