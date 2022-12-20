package com.example.mebankserver.service.impl;

import com.example.mebankserver.model.BankAccount;
import com.example.mebankserver.repository.BankAccountRepository;
import com.example.mebankserver.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BankAccountRepository bankAccountRepository;


    @Override
    public Mono<Long> getBalance(Long id) {
        return bankAccountRepository.findById(id)
                .map(BankAccount::getBalance);
    }

    @Override
    public Mono<Long> changeBalance(Long id, Long amount) {
        return bankAccountRepository.findById(id)
                .flatMap(bankAccount -> {
                    bankAccount.setBalance(bankAccount.getBalance() + amount);
                    return bankAccountRepository.save(bankAccount);
                })
                .map(BankAccount::getBalance);
    }

}