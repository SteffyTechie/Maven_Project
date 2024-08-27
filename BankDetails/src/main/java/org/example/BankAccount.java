package org.example;

import java.sql.*;
import java.util.Scanner;


public class BankAccount {



    public void addAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Account Number:");
        String acc_no = sc.next();
        System.out.println("Enter Account Name:");
        String acc_name = sc.next();
        System.out.println("Enter Initial Balance:");
        double balance = sc.nextDouble();
        System.out.println("Enter your pin:");
        String pin = sc.next();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication", "root", "mysql419");
            PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM bank_account WHERE acc_no = ?");
            Stmt.setString(1, acc_no);
            ResultSet rs = Stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Account already exists");
            } else {
                PreparedStatement pstmt = conn.prepareStatement( "INSERT INTO bank_account (acc_no, acc_name, balance, pin) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, acc_no);
                pstmt.setString(2, acc_name);
                pstmt.setDouble(3, balance);
                pstmt.setString(4, pin);
                pstmt.executeUpdate();
                System.out.println("Registration done");
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void debitAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Account Number:");
        String acc_no = sc.next();
        System.out.println("Enter your Pin:");
        String pin = sc.next();
        System.out.println("Enter the amount to debit:");
        double amount = sc.nextDouble();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication", "root", "mysql419");
            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM bank_account WHERE acc_no = ? AND pin = ?");
            checkStmt.setString(1, acc_no);
            checkStmt.setString(2, pin);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double currentamount = rs.getDouble("balance");
                if (currentamount >= amount) {
                    PreparedStatement updateStmt = conn.prepareStatement("UPDATE bank_account SET balance = balance -amount WHERE acc_no = acc_no AND pin = ?");
                    updateStmt.setDouble(1, amount);
                    updateStmt.setString(2, acc_no);
                    updateStmt.setString(3, pin);
                    updateStmt.executeUpdate();
                    System.out.println("Amount debited successfully");
                } else {
                    System.out.println("Insufficient balance");
                }
            } else {
                System.out.println("Invalid account or pin");
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void creditAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Account Number:");
        String acc_no = sc.next();
        System.out.println("Enter the amount to credit:");
        double amount = sc.nextDouble();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication", "root", "mysql419");
            PreparedStatement Stmt = conn.prepareStatement("SELECT * FROM bank_account WHERE acc_no = ?");
            Stmt.setString(1, acc_no);
            ResultSet rs = Stmt.executeQuery();

            if (rs.next()) {
                PreparedStatement updateStmt = conn.prepareStatement("UPDATE bank_account SET balance = balance + amount WHERE acc_no = acc_no");
                updateStmt.setDouble(1, amount);
                updateStmt.setString(2, acc_no);
                updateStmt.executeUpdate();
                System.out.println("Amount credited successfully");
            } else {
                System.out.println("Invalid account");
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAccount() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication", "root", "mysql419");

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bank_account");

            while (rs.next()) {
                String acc_no = rs.getString("acc_no");
                String acc_name = rs.getString("acc_name");
                double balance = rs.getDouble("balance");
                System.out.println("Account Number: " + acc_no + ", Account Name: " + acc_name + ", Balance: " + balance);
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

