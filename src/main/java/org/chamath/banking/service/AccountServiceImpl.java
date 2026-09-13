package org.chamath.banking.service;

import org.chamath.banking.exception.AccountNotFoundException;
import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;
import org.chamath.banking.model.Account;
import org.chamath.banking.model.CurrentAccount;
import org.chamath.banking.model.Customer;
import org.chamath.banking.model.SavingsAccount;
import org.chamath.banking.repository.AccountRepository;
import org.chamath.banking.repository.AccountRepositoryImpl;

import java.math.BigDecimal;
import java.util.HashMap;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
    public void viewAccount(String accountNumber) {
        try {
            Account account = accountRepository.getAccountByAccountNumber(accountNumber);
            System.out.println(account);
        } catch (AccountNotFoundException e) {
            e.getMessage();
        }
    }

    @Override
    public void viewAllAccounts() {
        HashMap<String, Account> accountHashMap = accountRepository.getAllAccounts();
        accountHashMap.values()
                .forEach(System.out::println);
    }

    @Override
    public void depositAccount(String accountNumber, double amount) {
        try {
            Account account = accountRepository.getAccountByAccountNumber(accountNumber);
            account.deposit(BigDecimal.valueOf(amount));
            accountRepository.saveAccount(account);
        } catch (AccountNotFoundException e) {
            e.getMessage();
        } catch (NegativeValueException e) {
            e.getMessage();
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        try {
            Account account = accountRepository.getAccountByAccountNumber(accountNumber);
            account.withdraw(BigDecimal.valueOf(amount));
            accountRepository.saveAccount(account);
        } catch (AccountNotFoundException e) {
            e.getMessage();
        } catch (NegativeValueException e) {
            e.getMessage();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
    }

    @Override
    public void transfer(String transferFromNumber, String transferToNumber, double amount) {
        try {
            Account transferFromAccount = accountRepository.getAccountByAccountNumber(transferFromNumber);
            Account transferToAccount = accountRepository.getAccountByAccountNumber(transferToNumber);
            transferFromAccount.transfer(transferToAccount, BigDecimal.valueOf(amount));
            accountRepository.saveAccount(transferFromAccount);
            accountRepository.saveAccount(transferToAccount);
        } catch (AccountNotFoundException e) {
            e.getMessage();
        } catch (NegativeValueException e) {
            e.getMessage();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
    }
}
