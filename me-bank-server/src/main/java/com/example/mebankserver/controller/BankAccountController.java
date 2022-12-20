package com.example.mebankserver.controller;

import com.example.mebankserver.model.BankAccount;
import com.example.mebankserver.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;


    @PostMapping
    public Mono<ResponseEntity<BankAccount>> create(@RequestParam Long balance) {
        return bankAccountService.create(new BankAccount(null, balance))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}