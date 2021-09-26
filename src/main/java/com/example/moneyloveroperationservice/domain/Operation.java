package com.example.moneyloveroperationservice.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Operation {
    Long id;
    Date date;
    Long walletId;
    Long categoryId;
    Long operationSum;
    String note;
}
