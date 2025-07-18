package com.market.simulator.accountservice.repository;

import com.market.simulator.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

  Account getAccountByUserId(String email);

}
