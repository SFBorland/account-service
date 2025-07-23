package com.market.simulator.accountservice.kafka;

import com.market.simulator.accountservice.exceptions.AccountAlreadyExistsException;
import com.market.simulator.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateAccountConsumer {

    private final AccountService accountService;

    public CreateAccountConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = "create-account", groupId = "account-service-consumer-group")
    public void listen(String message) {
        log.debug("message received, creating account for user: {}", message);
        try {
            accountService.createAccount(message);
        } catch (AccountAlreadyExistsException e) {
            //already logged in AccountService
        } catch (Exception e) {
            log.error("Error creating account for user: {}", message, e);
        }
    }
}
