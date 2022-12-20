package com.example.mebankserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceChangingDto {

    private Long balanceId;
    private Long amountToAdd;

}