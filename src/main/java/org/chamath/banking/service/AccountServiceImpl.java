package org.chamath.banking.service;

import org.chamath.banking.exception.NegativeValueException;
import org.chamath.banking.model.Account;
import org.chamath.banking.model.CurrentAccount;
import org.chamath.banking.model.Customer;
import org.chamath.banking.model.SavingsAccount;
import org.chamath.banking.repository.AccountRepositoryImpl;

import java.util.HashMap;

public class AccountServiceImpl implements AccountService {
    private AccountRepositoryImpl accountRepository;

    public AccountServiceImpl() {
        accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public void createSavingsAccount(Customer owner, double initialDeposit) {
        try {
            Account savingsAccount = new SavingsAccount(owner, initialDeposit);
            accountRepository.saveAccount(savingsAccount);
        } catch (NegativeValueException e) {
            e.getMessage();
        }
    }

    @Override
    public void createCurrentAccount(Customer owner, double initialDeposit) {
        try {
            Account currentAccount = new CurrentAccount(owner, initialDeposit);
            accountRepository.saveAccount(currentAccount);
        } catch (NegativeValueException e) {
            e.getMessage();
        }
    }

    @Override
    public Account viewAccount(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public HashMap<String, Account> viewAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public void depositAccount(String accountNumber, double amount) {

    }

    @Override
    public void withdraw(String accountNumber, double amount) {

    }

    @Override
    public void transfer(String transferFromNumber, String transferToNumber, double amount) {

    }
}
