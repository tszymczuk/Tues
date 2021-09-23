package com.company;

import java.util.ArrayList;
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
        while (true) {
            printMenu();
            var userChoice = menuReader.nextInt();
            switch (userChoice) {
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(menuReader);
                    break;
                case 3:
                    Customer current = selectCustomer(menuReader);
                    break;
                default:
                    System.out.println("You entered an incorrect menu option");
            }
        }
    }

    private Customer selectCustomer(Scanner reader) {
        System.out.print("What is the Customer's ID?: ");
        var idToFind = reader.nextInt();
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
        System.out.println("What would you like to do next? (Select the number):");
        System.out.println("      [1] Exit the Program");
        System.out.println("      [2] Add a Customer");
        System.out.println("      [3] Select Customer by ID");
        System.out.print(" Enter Choice: ");
    }
}
