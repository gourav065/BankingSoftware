package com.bankingsystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;    //Deposite, Withdraw, Transfer
    private double amount;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private Account targetAccount;

    // **Add this no-arg constructor**
    public Transaction() {}

//    Simplified Constructor (auto timestamp)
    public Transaction(String type, double amount, Account sourceAccount, Account targetAccount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now(); // auto assign
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
    }

//    Full Constructor (agar manually timestamp dena ho)
    public Transaction(String type, double amount, LocalDateTime timestamp, Account sourceAccount, Account targetAccount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }
}
