package com.example.moneyloveroperationservice.conroller;

import com.example.moneyloveroperationservice.domain.Operation;
import com.example.moneyloveroperationservice.service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/operations")
@AllArgsConstructor
public class OperationController {
    private final OperationService operationService;

    public Long getIncomeInOperationsByDate(Date from, Date to) {
        return 1L;
    }

    public Long getExpenseInOperationsByDate(Date from, Date to) {
        return 1L;
    }

    public List<Operation> getOperationsByDate(Date from, Date to) {
        return new ArrayList<>();
    }

    @PostMapping
    public Operation saveOperation(@RequestBody Operation operation) {
        return operationService.saveOperationAndThenUpdateWalletBalance(operation);
    }
}
