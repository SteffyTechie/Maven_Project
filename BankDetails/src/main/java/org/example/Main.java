package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choice;

        Scanner sc=new Scanner(System.in);

        BankAccount bank= new BankAccount();


        do{
            System.out.println("Details"+
                    "\n 1 Add Account" +
                    "\n 2 CreditAmount " +
                    "\n 3 DebitAmount " +
                    "\n 4 show All Accounts \n Enter your choice:");
            choice= sc.nextInt();

            switch (choice) {
                case 1:
                    bank.addAccount();
                    break;
                case 2:
                    bank.creditAmount();
                    break;
                case 3:
                    bank.debitAmount();
                    break;
                case 4:
                    bank.showAccount();
                    break;
                case 5:
                    System.out.println("Exiting program");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }while(choice !=5);
        }
    }
