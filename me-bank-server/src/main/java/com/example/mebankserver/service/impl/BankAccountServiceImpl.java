package com.example.mebankserver.service.impl;

import com.example.mebankserver.model.BankAccount;
import com.example.mebankserver.repository.BankAccountRepository;
import com.example.mebankserver.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;


    @Override
    public Mono<BankAccount> create(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

}
