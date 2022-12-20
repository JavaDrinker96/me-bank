package com.example.mebankserver.repository;

import com.example.mebankserver.model.BankAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends ReactiveCrudRepository<BankAccount, Long> {
}