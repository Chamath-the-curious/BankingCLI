package org.chamath.banking.repository;

import org.chamath.banking.model.Account;

import java.util.HashMap;

public interface AccountRepository {
    public void saveAccount(Account account);
    public Account getAccountByAccountNumber(String accountNumber);
    public HashMap<String, Account> getAllAccounts();
}
