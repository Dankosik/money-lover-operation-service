package com.example.moneyloveroperationservice.service.impl;

import com.example.moneyloveroperationservice.domain.Category;
import com.example.moneyloveroperationservice.domain.CategoryType;
import com.example.moneyloveroperationservice.domain.Operation;
import com.example.moneyloveroperationservice.repository.CategoryRepository;
import com.example.moneyloveroperationservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Operation> filterOperationsByCategoryType(List<Operation> operations, CategoryType categoryType) {
        return operations.stream()
                .filter(operation -> categoryRepository.findById(operation.getCategory().getId())
                        .get().getCategoryType().equals(categoryType))
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(Long id) {
        boolean isCategoryPresent = categoryRepository.findById(id).isPresent();
        log.debug("Category is present: {}", isCategoryPresent);
        if (isCategoryPresent) {
            return categoryRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Category with id: " + id + " is not found");
    }
}
