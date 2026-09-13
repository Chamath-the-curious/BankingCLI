package org.chamath.banking.repository;

import org.chamath.banking.model.Account;

import java.util.HashMap;

public class AccountRepositoryImpl implements AccountRepository {
    private HashMap<String, Account> accountHashMap;

    public AccountRepositoryImpl() {
        accountHashMap = new HashMap<>();
    }

    @Override
    public void saveAccount(Account account) {
        accountHashMap.put(account.getAccountNumber(), account);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountHashMap.get(accountNumber);
    }

    @Override
    public HashMap<String, Account> getAllAccounts() {
        return accountHashMap;
    }
}
