package com.market.simulator.accountservice.service;

import com.market.simulator.accountservice.exceptions.AccountAlreadyExistsException;
import com.market.simulator.accountservice.exceptions.AccountNotFoundException;
import com.market.simulator.accountservice.model.Account;
import com.market.simulator.accountservice.model.AccountStatus;
import com.market.simulator.accountservice.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account getAccountByUserId(String email) {
    Account account = accountRepository.getAccountByUserId(email);

    if (account == null) {
      throw new AccountNotFoundException("Account not found for user: " + email);
    }
    return account;
  }

  public Account createAccount(String userId) {
    if (accountRepository.existsByUserId(userId)) {
      throw new AccountAlreadyExistsException("Account already exists for user: " + userId);
    }

    Account account = new Account();
    account.setUserId(userId);
    account.setAccountStatus(AccountStatus.ACTIVE);

    log.info("Creating account for user: {}", userId);
    return accountRepository.save(account);
  }
}
