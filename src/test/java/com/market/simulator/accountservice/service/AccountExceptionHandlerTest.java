package com.market.simulator.accountservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.market.simulator.accountservice.exceptions.AccountAlreadyExistsException;
import com.market.simulator.accountservice.exceptions.AccountNotFoundException;
import com.market.simulator.accountservice.model.Account;
import com.market.simulator.accountservice.model.AccountStatus;
import com.market.simulator.accountservice.repository.AccountRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountExceptionHandlerTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountService accountService;

  @Test
  public void getAccountByUserId_returnsAccount() {
    String userId = "some@user.com";
    Account account = new Account();
    account.setUserId(userId);

    Mockito.when(accountRepository.getAccountByUserId(userId))
            .thenReturn(account);
    Account result = accountService.getAccountByUserId(userId);

    assertNotNull(result);
    assertEquals(userId, result.getUserId());
  }

  @Test
  public void createAccount_returnsAccountWithExpectedFields() {
    String userId = "some@user.com";
    UUID accountId = UUID.randomUUID();
    Account account = new Account();
    account.setAccountId(accountId);
    account.setUserId(userId);
    account.setAccountStatus(AccountStatus.ACTIVE);

    Mockito.when(accountRepository.existsById(userId))
        .thenReturn(false);

    Mockito.when(accountRepository.save(Mockito.any(Account.class)))
        .thenReturn(account);

    Account resultAccount = accountService.createAccount(userId);

    assertEquals(accountId, resultAccount.getAccountId());
  }


  @Test
  public void handleAccountNotFoundException_returnsNotFoundWithMessage() {
    assertThrows(AccountNotFoundException.class, () -> {
      String userId = "notfound@user.com";
      Mockito.when(accountRepository.getAccountByUserId(userId))
              .thenReturn(null);
      accountService.getAccountByUserId(userId);
    });
  }

  @Test
  public void handleAccountAlreadyExistsException_returnsConflictWithMessage() {
    assertThrows(AccountAlreadyExistsException.class, () -> {
      String userId = "dup@user.com";
      Account account = new Account();
      account.setUserId(userId);

      Mockito.when(accountRepository.existsById(userId))
              .thenReturn(true);

      accountService.createAccount(userId);
    });
  }
}
