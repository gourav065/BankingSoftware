package com.bankingsystem.controller;

import com.bankingsystem.model.Transaction;
import com.bankingsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Deposit money
    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam Long accountId, @RequestParam double amount) {
        return  transactionService.deposite(accountId,amount);
    }

    // Withdraw money
    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam Long accountId, @RequestParam double amount) {
        return transactionService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public Transaction transfer(
            @RequestParam Long sourceAccountId,
            @RequestParam Long targetAccountId,
            @RequestParam double amount
    ) {
        return transactionService.transfer(sourceAccountId, targetAccountId, amount);
    }

    // Get all transactions
    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}