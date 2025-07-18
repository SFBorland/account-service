package com.market.simulator.accountservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
public class Account {

  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID accountId;

  @Column(nullable = false, unique = true)
  private String userId;

  @Column(nullable = false)
  private AccountStatus accountStatus;

  private StatusReason statusReason;

  @Column(nullable = false)
  private BigDecimal balance;

  @Column(nullable = false)
  private BigDecimal availableBalance;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

@PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
