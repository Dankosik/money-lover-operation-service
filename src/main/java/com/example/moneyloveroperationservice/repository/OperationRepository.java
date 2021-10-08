package com.example.moneyloveroperationservice.repository;

import com.example.moneyloveroperationservice.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findOperationByDateBetween(Date date, Date date2);
}
