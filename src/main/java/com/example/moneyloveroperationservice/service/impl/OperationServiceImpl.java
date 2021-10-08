package com.example.moneyloveroperationservice.service.impl;

import com.example.moneyloveroperationservice.domain.CategoryType;
import com.example.moneyloveroperationservice.domain.Operation;
import com.example.moneyloveroperationservice.repository.OperationRepository;
import com.example.moneyloveroperationservice.service.CategoryService;
import com.example.moneyloveroperationservice.service.OperationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final AmqpTemplate amqpTemplate;
    private final CategoryService categoryService;

    @Override
    public Operation saveOperationAndThenUpdateWalletBalance(Operation operation) {
        Operation savedOperation = operationRepository.save(operation);
        amqpTemplate.convertAndSend("operation", "operation.event.created", savedOperation);
        return savedOperation;
    }

    @Override
    public Operation getOperationById(Long id) {
        boolean isOperationPresent = operationRepository.findById(id).isPresent();
        log.debug("Operation is present: {}", isOperationPresent);
        if (isOperationPresent) {
            return operationRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Operation with id: " + id + " is not found");
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public void deleteOperationById(Long id) {
        boolean isOperationPresent = operationRepository.findById(id).isPresent();
        log.debug("Operation is present: {}", isOperationPresent);
        if (isOperationPresent) {
            operationRepository.deleteById(getOperationById(id).getId());
        } else {
            throw new EntityNotFoundException("Operation with id: " + id + " is not found");
        }
    }

    @Override
    public List<Operation> saveAllOperations(List<Operation> operations) {
        List<Operation> result = new ArrayList<>();
        operations.forEach(operation -> result.add(operationRepository.save(operation)));
        return result;
    }

    @Override
    public List<Operation> getOperationsByDate(Date first, Date second) {
        return operationRepository.findOperationByDateBetween(first, second);
    }

    @Override
    public Long getIncomeInOperations(List<Operation> operations) {
        return categoryService.filterOperationsByCategoryType(operations, CategoryType.INCOME)
                .stream()
                .map(Operation::getOperationSum)
                .mapToLong(Long::longValue)
                .sum();
    }

    @Override
    public Long getExpenseInOperations(List<Operation> operations) {
        return categoryService.filterOperationsByCategoryType(operations, CategoryType.EXPENSE)
                .stream()
                .map(Operation::getOperationSum)
                .mapToLong(Long::longValue)
                .sum();
    }
}

