package com.market.simulator.accountservice.event.consumer;

import com.market.simulator.accountservice.exceptions.AccountAlreadyExistsException;
import com.market.simulator.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountCreateConsumer {

  private final AccountService accountService;

  public AccountCreateConsumer(AccountService accountService) {
    this.accountService = accountService;
  }

//  @Scheduled(initialDelayString = "5000", fixedRate = 5000)
  public void consume() {
    try {
      accountService.createAccount("scheduled@user.com");
    } catch (AccountAlreadyExistsException ex) {
      //ExceptionHandler will only handle exceptions thrown by REST controllers, so we log it here.
      log.warn(ex.getMessage());
    }
  }
}
