package com.market.simulator.accountservice.controller;

import com.market.simulator.accountservice.model.Account;
import com.market.simulator.accountservice.service.AccountService;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(name = "/account")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<Account> getAccountByUserId(@Email @PathVariable String email) {
    Account account = accountService.getAccountByUserId(email);
    return ResponseEntity.ok(account);
  }
}
