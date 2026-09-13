
package org.chamath.banking.service;

import org.chamath.banking.exception.CustomerNotFoundException;
import org.chamath.banking.model.Account;
import org.chamath.banking.model.Customer;
import org.chamath.banking.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private Customer customer;

    @Mock
    private Account account;

    private final String nic = "200012345678";

    @Test
    void createCustomer_shouldSaveCustomer() {
        customerService.createCustomer(
                nic,
                "Chamath",
                "chamath@example.com",
                "0712345678"
        );

        ArgumentCaptor<Customer> captor =
                ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).saveCustomer(captor.capture());

        assertNotNull(captor.getValue());
        assertInstanceOf(Customer.class, captor.getValue());
    }

    @Test
    void viewCustomer_shouldRetrieveCustomer() throws Exception {
        when(customerRepository.getCustomerByCustomerId(nic))
                .thenReturn(customer);

        customerService.viewCustomer(nic);

        verify(customerRepository).getCustomerByCustomerId(nic);
    }

    @Test
    void viewCustomer_shouldHandleCustomerNotFound()
            throws Exception {

        when(customerRepository.getCustomerByCustomerId(nic))
                .thenThrow(new CustomerNotFoundException());

        assertDoesNotThrow(
                () -> customerService.viewCustomer(nic)
        );

        verify(customerRepository).getCustomerByCustomerId(nic);
        verify(customerRepository, never()).saveCustomer(any());
    }

    @Test
    void viewAllCustomers_shouldRetrieveAllCustomers() {
        HashMap<String, Customer> customers = new HashMap<>();

        customers.put(nic, customer);
        customers.put("199912345678", mock(Customer.class));

        when(customerRepository.getAllCustomers())
                .thenReturn(customers);

        customerService.viewAllCustomers();

        verify(customerRepository).getAllCustomers();
    }

    @Test
    void viewAllCustomers_shouldHandleEmptyCustomerMap() {
        when(customerRepository.getAllCustomers())
                .thenReturn(new HashMap<>());

        assertDoesNotThrow(
                () -> customerService.viewAllCustomers()
        );

        verify(customerRepository).getAllCustomers();
    }

    @Test
    void linkAccountToCustomer_shouldAddAndSaveAccount()
            throws Exception {

        when(customerRepository.getCustomerByCustomerId(nic))
                .thenReturn(customer);

        customerService.linkAccountToCustomer(nic, account);

        verify(customer).addAccount(account);
        verify(customerRepository).saveCustomer(customer);
    }

    @Test
    void linkAccountToCustomer_shouldHandleCustomerNotFound()
            throws Exception {

        when(customerRepository.getCustomerByCustomerId(nic))
                .thenThrow(new CustomerNotFoundException());

        assertDoesNotThrow(
                () -> customerService.linkAccountToCustomer(nic, account)
        );

        verify(customerRepository).getCustomerByCustomerId(nic);

        verify(customer, never()).addAccount(any());
        verify(customerRepository, never()).saveCustomer(any());
    }
}