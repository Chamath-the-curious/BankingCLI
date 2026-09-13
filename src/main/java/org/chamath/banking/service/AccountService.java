package org.chamath.banking.service;

import org.chamath.banking.model.Account;
import org.chamath.banking.model.Customer;

import java.util.HashMap;

public interface AccountService {
    public void createSavingsAccount(Customer owner, double initialDeposit);
    public void createCurrentAccount(Customer owner, double initialDeposit);
    public Account viewAccount(String accountNumber);
    public HashMap<String, Account> viewAllAccounts();
    public void depositAccount(String accountNumber, double amount);
    public void withdraw(String accountNumber, double amount);
    public void transfer(String transferFromNumber, String transferToNumber, double amount);
}
