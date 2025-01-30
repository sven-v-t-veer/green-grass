package com.blueharvest.service;

import com.blueharvest.exception.AccountNotFoundException;
import com.blueharvest.exception.CustomerNotFoundException;
import com.blueharvest.exception.InsufficientFundsException;
import com.blueharvest.spi.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {

    Account createAccount(UUID customerId, BigDecimal initialBalance) throws CustomerNotFoundException, AccountNotFoundException;
    Account deposit(UUID customerId, UUID accountId, String description, BigDecimal amount) throws CustomerNotFoundException, AccountNotFoundException;
    Account withdraw(UUID customerId, UUID accountId, String description, BigDecimal amount) throws CustomerNotFoundException, AccountNotFoundException, InsufficientFundsException;
}
