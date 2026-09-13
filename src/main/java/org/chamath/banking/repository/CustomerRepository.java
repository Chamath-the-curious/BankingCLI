package org.chamath.banking.repository;

import org.chamath.banking.exception.CustomerNotFoundException;
import org.chamath.banking.model.Customer;

import java.util.HashMap;

public interface CustomerRepository {
    public void saveCustomer(Customer customer);
    public Customer getCustomerByCustomerId(String customerId) throws CustomerNotFoundException;
    public HashMap<String, Customer> getAllCustomers();
}
