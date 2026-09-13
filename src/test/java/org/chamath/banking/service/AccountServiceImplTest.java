
package org.chamath.banking.service;

import org.chamath.banking.exception.InsufficientFundsException;
import org.chamath.banking.exception.NegativeValueException;
import org.chamath.banking.model.Account;
import org.chamath.banking.model.CurrentAccount;
import org.chamath.banking.model.Customer;
import org.chamath.banking.model.SavingsAccount;
import org.chamath.banking.repository.AccountRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private Customer customer;

    @Mock
    private Account account;

    @Mock
    private Account anotherAccount;

    private final String accountNumber = "1234567890";

    @BeforeEach
    void setUp() {
        // Common setup can be added here.
    }

    @Test
    void createSavingsAccount_shouldSaveSavingsAccount() {
        accountService.createSavingsAccount(customer, 1000.0);

        ArgumentCaptor<Account> captor =
                ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).saveAccount(captor.capture());

        assertInstanceOf(SavingsAccount.class, captor.getValue());
    }

    @Test
    void createCurrentAccount_shouldSaveCurrentAccount() {
        accountService.createCurrentAccount(customer, 1000.0);

        ArgumentCaptor<Account> captor =
                ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).saveAccount(captor.capture());

        assertInstanceOf(CurrentAccount.class, captor.getValue());
    }

    @Test
    void viewAccount_shouldRetrieveAccount() throws Exception {
        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        accountService.viewAccount(accountNumber);

        verify(accountRepository)
                .getAccountByAccountNumber(accountNumber);
    }

    @Test
    void viewAllAccounts_shouldRetrieveAllAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put(accountNumber, account);
        accounts.put("0987654321", anotherAccount);

        when(accountRepository.getAllAccounts())
                .thenReturn(accounts);

        accountService.viewAllAccounts();

        verify(accountRepository).getAllAccounts();
    }

    @Test
    void depositAccount_shouldDepositAndSaveAccount() throws Exception {
        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        accountService.depositAccount(accountNumber, 500.0);

        verify(account).deposit(BigDecimal.valueOf(500.0));
        verify(accountRepository).saveAccount(account);
    }

    @Test
    void depositAccount_shouldNotSaveWhenAmountIsNegative()
            throws Exception {

        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        doThrow(new NegativeValueException())
                .when(account).deposit(BigDecimal.valueOf(-100.0));

        accountService.depositAccount(accountNumber, -100.0);

        verify(account).deposit(BigDecimal.valueOf(-100.0));
        verify(accountRepository, never()).saveAccount(any());
    }

    @Test
    void withdraw_shouldWithdrawAndSaveAccount() throws Exception {
        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        accountService.withdraw(accountNumber, 200.0);

        verify(account).withdraw(BigDecimal.valueOf(200.0));
        verify(accountRepository).saveAccount(account);
    }

    @Test
    void withdraw_shouldNotSaveWhenAmountIsNegative()
            throws Exception {

        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        doThrow(new NegativeValueException())
                .when(account).withdraw(BigDecimal.valueOf(-100.0));

        accountService.withdraw(accountNumber, -100.0);

        verify(account).withdraw(BigDecimal.valueOf(-100.0));
        verify(accountRepository, never()).saveAccount(any());
    }

    @Test
    void withdraw_shouldNotSaveWhenFundsAreInsufficient()
            throws Exception {

        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        doThrow(new InsufficientFundsException())
                .when(account).withdraw(BigDecimal.valueOf(1000.0));

        accountService.withdraw(accountNumber, 1000.0);

        verify(account).withdraw(BigDecimal.valueOf(1000.0));
        verify(accountRepository, never()).saveAccount(any());
    }

    @Test
    void transfer_shouldTransferBetweenAccounts() throws Exception {
        String destinationAccountNumber = "9876543210";

        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        when(accountRepository.getAccountByAccountNumber(
                destinationAccountNumber))
                .thenReturn(anotherAccount);

        accountService.transfer(
                accountNumber,
                destinationAccountNumber,
                300.0
        );

        verify(account).transfer(
                anotherAccount,
                BigDecimal.valueOf(300.0)
        );
    }

    @Test
    void transfer_shouldNotSaveWhenFundsAreInsufficient()
            throws Exception {

        String destinationAccountNumber = "9876543210";

        when(accountRepository.getAccountByAccountNumber(accountNumber))
                .thenReturn(account);

        when(accountRepository.getAccountByAccountNumber(
                destinationAccountNumber))
                .thenReturn(anotherAccount);

        doThrow(new InsufficientFundsException())
                .when(account).transfer(
                        anotherAccount,
                        BigDecimal.valueOf(5000.0)
                );

        accountService.transfer(
                accountNumber,
                destinationAccountNumber,
                5000.0
        );

        verify(account).transfer(
                anotherAccount,
                BigDecimal.valueOf(5000.0)
        );

        verify(accountRepository, never()).saveAccount(any());
    }
}