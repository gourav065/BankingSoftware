package com.bankingsystem.service;

import com.bankingsystem.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction deposite(Long accountId, double amount);
    Transaction withdraw(Long accountId, double amount);
    Transaction transfer(Long sourceId, Long targetId, double amount);
    List<Transaction> getAllTransactions();
}
