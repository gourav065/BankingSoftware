package com.bankingsystem.service;

import com.bankingsystem.model.Account;
import com.bankingsystem.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository acctRepo;

    public AccountServiceImpl(AccountRepository acctRepo){
        this.acctRepo = acctRepo;
    }

    public Account createAccount(Account account){
        return acctRepo.save(account);
    }

    @Override
    public List<Account> getAllAccounts(){
        return acctRepo.findAll();
    }
}
