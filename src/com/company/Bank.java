package com.company;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private ArrayList<BankAccount> allAccounts;
    private ArrayList<Customer> allCustomers;

    public Bank() {
        allAccounts = new ArrayList<BankAccount>();
        allCustomers = new ArrayList<Customer>();
    }

    public void doBanking() {
        var menuReader = new Scanner(System.in);
        while(true) {
            printMenu();
            var userChoice = menuReader.nextInt();
            switch (userChoice) {
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(menuReader);
                    break;
                case 3:
                    Optional<Customer> current = selectCustomer(menuReader);
                    if (current.isPresent())
                        doCustomerMenu(menuReader, current.get());
                    else
                        System.out.println("No customer with that ID found.");
                    break;
                case 4:
                    doYearlyMaintenance();
                    break;
                default:
                    System.out.println("You entered an incorrect menu option, try again");
            }
        }
    }

    private void doYearlyMaintenance() {
        //for each account - call addInterest and then print account info
        for (var currentAccount : allAccounts) {
            currentAccount.addInterest();
            System.out.println("Account ID: " + currentAccount.getAccountID() + " has a balance of " + currentAccount.checkBalance());
        }
    }

    private void doCustomerMenu(Scanner menuReader, Customer currentCustomer) {
        while(true) {
            printCustomerMenu();
            var customerChoice = menuReader.nextInt();
            switch (customerChoice){
                case 1:
                    openCustomerAccount(menuReader, currentCustomer);
                    break;
                case 2:
                    closeCustomerAccount(menuReader, currentCustomer);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("You entered an incorrect menu option, try again");
            }
        }
    }

    private void closeCustomerAccount(Scanner menuReader, Customer currentCustomer) {
        //ask the user what account number to close
        System.out.print("Please enter the account number to close: ");
        var accountNum = menuReader.nextInt();
        //call close account on the customer
        Optional<BankAccount> accountToClose = currentCustomer.closeAccount(accountNum);
        //if the close succeeded remove the account from allAccounts
        if (accountToClose.isPresent()) {
            allAccounts.remove(accountToClose.get());
        }
    }

    private void openCustomerAccount(Scanner menuReader, Customer currentCustomer) {
        //ask the user how much money the starting deposit is
        System.out.print("Please enter the initial deposit the Customer is adding: ");
        var initialDeposit = menuReader.nextDouble();
        //call open account on the customer
        var newAccount = currentCustomer.openAccount(initialDeposit);
        //add the new account to allAccounts
        allAccounts.add(newAccount);
    }

    private void printCustomerMenu() {
        System.out.println("******************************************");
        System.out.println("What do you want to do with this customer? (Select the Number):");
        System.out.println("    [1] Open New Account");
        System.out.println("    [2] Close an account");
        System.out.println("    [3] Return to Main Menu");
        System.out.println("******************************************");
        System.out.print(" Enter Choice: ");
    }

    private Optional <Customer> selectCustomer(Scanner reader) {
        System.out.print("What is the Customer's ID?: ");
        var idToFind = reader.nextInt();
        for (var currentCustomer: allCustomers){
            if(currentCustomer.getID() == idToFind)
                return Optional.of(currentCustomer);
        }
        return Optional.empty();
    }

    private void addCustomer(Scanner inputReader) {
        inputReader.nextLine(); //Eat the orphan newLine from previous nextInt call
        System.out.print("What is the new Customer's name?: ");
        var customerName = inputReader.nextLine();
        System.out.print("What is the new Customer's Tax ID (SSN): ");
        var taxID = inputReader.nextInt();
        var newCustomer = new Customer(customerName, taxID);
        allCustomers.add(newCustomer);
    }

    private void printMenu() {
        System.out.println("====================================================");
        System.out.println("What would you like to do next? (Select the number):");
        System.out.println("      [1] Exit the Program");
        System.out.println("      [2] Add a Customer");
        System.out.println("      [3] Select Customer by ID");
        System.out.println("      [4] Do the yearly maintenance and show all accounts");
        System.out.println("====================================================");
        System.out.print(" Enter Choice: ");
    }
}
