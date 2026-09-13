package org.chamath.banking.service;

import org.chamath.banking.exception.CustomerNotFoundException;
import org.chamath.banking.model.Account;
import org.chamath.banking.model.Customer;
import org.chamath.banking.repository.CustomerRepository;

import java.util.HashMap;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(String nic, String name, String email, String phone) {
        Customer customer = new Customer(nic, name, email, phone);
        customerRepository.saveCustomer(customer);
    }

    @Override
    public void viewCustomer(String nic) {
        try {
            Customer customer = customerRepository.getCustomerByCustomerId(nic);
            System.out.println(customer);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }
    }

    @Override
    public void viewAllCustomers() {
        HashMap<String, Customer> customerHashMap = customerRepository.getAllCustomers();
        customerHashMap.values()
                .forEach(System.out::println);
    }

    @Override
    public void linkAccountToCustomer(String nic, Account account) {
        try {
            Customer customer = customerRepository.getCustomerByCustomerId(nic);
            customer.addAccount(account);
            customerRepository.saveCustomer(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println("Customer not found");
        }
    }
}
