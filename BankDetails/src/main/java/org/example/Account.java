package org.example;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "AccountNumber", nullable = false, unique = true)
    public String accNo;
    @Column(name = "AccountName", nullable = false)
    public String accName;
    @Column(name = "Balance", nullable = false)
    public double balance;
    @Column(name = "Pin", nullable = false)
    public String pin;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accNo='" + accNo + '\'' +
                ", accName='" + accName + '\'' +
                ", balance=" + balance +
                ", pin='" + pin + '\'' +
                '}';
    }

    public String getAccName() {
        return accName;
    }

    public Account() {
    }

    public Account(String accNo, String accName, double balance, String pin) {

        this.accNo = accNo;
        this.accName = accName;
        this.balance = balance;
        this.pin = pin;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
