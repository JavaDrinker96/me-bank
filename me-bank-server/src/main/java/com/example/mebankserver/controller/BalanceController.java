package com.example.mebankserver.controller;

import com.example.mebankserver.dto.BalanceChangingDto;
import com.example.mebankserver.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;


    @GetMapping("/{id}")
    public Mono<ResponseEntity<Long>> getBalance(@PathVariable Long id) {
        log.info("Try get balance for id = {}.", id);
        return balanceService.getBalance(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping
    public Mono<ResponseEntity<Long>> changeBalance(@RequestBody BalanceChangingDto dto) {
        log.info("Try change balance for id = {} with amount = {}.", dto.getBalanceId(), dto.getAmountToAdd());
        return balanceService.changeBalance(dto.getBalanceId(), dto.getAmountToAdd())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}