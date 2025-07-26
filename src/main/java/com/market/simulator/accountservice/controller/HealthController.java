package com.market.simulator.accountservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account/health")
public class HealthController {

  @GetMapping("/RUOK")
  public String test() {
    log.info("Received health check request for Account Service");
    return "IMOK\n";
  }
}
