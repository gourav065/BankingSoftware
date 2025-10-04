package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public Account create(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAll(){
        return accountService.getAllAccounts();
    }
}
