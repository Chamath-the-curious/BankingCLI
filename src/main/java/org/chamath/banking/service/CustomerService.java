package org.chamath.banking.service;

import org.chamath.banking.exception.CustomerNotFoundException;
import org.chamath.banking.model.Account;

public interface CustomerService {
    public void createCustomer(String nic, String name, String email, String phone);
    public void viewCustomer(String nic);
    public void viewAllCustomers();
    public void linkAccountToCustomer(String nic, Account account);
}
