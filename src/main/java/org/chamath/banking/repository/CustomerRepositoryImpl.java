package org.chamath.banking.repository;

import org.chamath.banking.exception.CustomerNotFoundException;
import org.chamath.banking.model.Customer;

import java.util.HashMap;

public class CustomerRepositoryImpl implements CustomerRepository {
    private HashMap<String, Customer> customerHashMap;

    public CustomerRepositoryImpl() {
        customerHashMap = new HashMap<>();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerHashMap.put(customer.getCustomerId(), customer);
    }

    @Override
    public Customer getCustomerByCustomerId(String customerId) throws CustomerNotFoundException {
        if (customerHashMap.containsKey(customerId)) {
            return customerHashMap.get(customerId);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public HashMap<String, Customer> getAllCustomers() {
        return customerHashMap;
    }
}
