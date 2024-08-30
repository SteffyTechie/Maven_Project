package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Scanner;


public class BankAccount {
    private  SessionFactory sessionFactory;

    public BankAccount(){
        sessionFactory =new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

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


        Account ac=new Account(acc_no,acc_name,balance,pin);
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        session.persist(ac);
        tx.commit();
        session.close();
        System.out.println("successfully done");

    }

    public void debitAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Account Number:");
        String accNo = sc.next();
        System.out.println("Enter your Pin:");
        String pin = sc.next();
        System.out.println("Enter the amount to debit:");
        double amount = sc.nextDouble();


        Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();

            Account account = (Account) session.createQuery("FROM Account WHERE accNo = :accNo AND pin = :pin")
                    .setParameter("accNo", accNo)
                    .setParameter("pin", pin)
                    .uniqueResult();

            if (account != null) {
                double currentBalance = account.getBalance();
                if (currentBalance >= amount) {
                    account.setBalance(currentBalance - amount);

                    session.update(account);

                    System.out.println("Amount debited successfully");
                } else {
                    System.out.println("Insufficient balance");
                }
            } else {
                System.out.println("Invalid account or pin");
            }
            tx.commit();
            session.close();
            sessionFactory.close();
        }

    public void creditAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Account Number:");
        String accNo = sc.next();
        System.out.println("Enter the amount to credit:");
        double amount = sc.nextDouble();

        Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();

            Account account = (Account) session.createQuery("FROM Account WHERE accNo = :accNo")
                    .setParameter("accNo", accNo)
                    .uniqueResult();

            if (account != null) {

                account.setBalance(account.getBalance() + amount);


                session.update(account);
                tx.commit();
                System.out.println("Amount credited successfully");
            } else {
                System.out.println("Invalid account");
            }

            session.close();
            sessionFactory.close();
        }



    public void showAccount() {

             Session session = sessionFactory.openSession();


            List<Account> accounts = session.createQuery("FROM Account", Account.class).list();
            session.close();

            accounts.stream()
                    .forEach(System.out::println);




    }
}

