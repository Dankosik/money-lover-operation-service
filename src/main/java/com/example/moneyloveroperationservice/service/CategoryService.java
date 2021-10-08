package com.example.moneyloveroperationservice.service;

import com.example.moneyloveroperationservice.domain.Category;
import com.example.moneyloveroperationservice.domain.CategoryType;
import com.example.moneyloveroperationservice.domain.Operation;

import java.util.List;

public interface CategoryService {
    List<Operation> filterOperationsByCategoryType(List<Operation> operations, CategoryType categoryType);

    Category getCategoryById(Long id);
}
