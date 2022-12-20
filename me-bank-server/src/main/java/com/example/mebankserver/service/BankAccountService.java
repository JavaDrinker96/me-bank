package com.example.mebankserver.service;

import com.example.mebankserver.model.BankAccount;
import reactor.core.publisher.Mono;

public interface BankAccountService {

    Mono<BankAccount> create(BankAccount bankAccount);

}