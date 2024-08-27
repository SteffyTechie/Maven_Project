package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //create a object to the class
        UserEntry obj=new UserEntry();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu" +
                    " \n 1. Login " +
                    "\n 2. Register " +
                    "\n 3. Exit " +
                    "\n Enter your choice ");

            choice = sc.nextInt();

            String username = "";
            String password = "";
            String name = "";


            switch (choice) {
                case 1:
                    UserEntry.login(username, password);
                    break;

               case 2:
                 UserEntry.register(name,username, password);
                   break;

                default:
                    System.out.println("Invalid choice");
            }

        } while(choice != 3);
    }
}
