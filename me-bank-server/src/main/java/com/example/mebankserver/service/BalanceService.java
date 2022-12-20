package com.example.mebankserver.service;

import reactor.core.publisher.Mono;

public interface BalanceService {

    Mono<Long> getBalance(Long id);

    Mono<Long> changeBalance(Long id, Long amount);

}