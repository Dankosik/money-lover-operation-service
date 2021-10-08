package com.example.moneyloveroperationservice.service;

import com.example.moneyloveroperationservice.domain.Operation;

import java.util.Date;
import java.util.List;

public interface OperationService {
    Operation saveOperationAndThenUpdateWalletBalance(Operation operation);

    Operation getOperationById(Long id);

    List<Operation> getAllOperations();

    void deleteOperationById(Long id);

    List<Operation> saveAllOperations(List<Operation> operations);

    List<Operation> getOperationsByDate(Date first, Date second);

    Long getIncomeInOperations(List<Operation> operations);

    Long getExpenseInOperations(List<Operation> operations);
}
