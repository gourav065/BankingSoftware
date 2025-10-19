package com.bankingsystem.service;

import com.bankingsystem.exception.ResourceNotFoundException;
import com.bankingsystem.model.Account;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction deposite(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction tx = new Transaction("DEPOSIT", amount, account, null);
        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public Transaction withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getBalance() < amount) {
            throw new ResourceNotFoundException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction tx = new Transaction("WITHDRAW", amount, account, null);
        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public Transaction transfer(Long sourceId, Long targetId, double amount) {
        Account source = accountRepository.findById(sourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Source account not found"));
        Account target = accountRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target account not found"));

        if (source.getBalance() < amount) {
            throw new ResourceNotFoundException("Insufficient balance for transfer");
        }

        source.setBalance(source.getBalance() - amount);
        target.setBalance(target.getBalance() + amount);
        accountRepository.save(source);
        accountRepository.save(target);

        Transaction tx = new Transaction("TRANSFER", amount, source, target);
        return transactionRepository.save(tx);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
