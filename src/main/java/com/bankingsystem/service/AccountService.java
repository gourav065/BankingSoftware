package com.bankingsystem.service;

import com.bankingsystem.model.Account;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
}
