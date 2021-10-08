package com.example.moneyloveroperationservice.conroller;

import com.example.moneyloveroperationservice.domain.Category;
import com.example.moneyloveroperationservice.domain.CategoryType;
import com.example.moneyloveroperationservice.domain.Operation;
import com.example.moneyloveroperationservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/operations/categories/")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("filter/{categoryType}")
    List<Operation> filterOperationsByCategoryType(List<Operation> operations, @PathVariable String categoryType) {
        if (categoryType.equals("INCOME")) {
            return categoryService.filterOperationsByCategoryType(operations, CategoryType.INCOME);
        } else if (categoryType.equals("EXPENSE")) {
            return categoryService.filterOperationsByCategoryType(operations, CategoryType.EXPENSE);
        }
        throw new RuntimeException();
    }

    List<Category> getCategoriesWithoutParent() {
        return new ArrayList<>();
    }

    Category getCategoryByName(String name) {
        return new Category();
    }

    @GetMapping("{id}")
    Category getCategoryById(@PathVariable String id) {
        return categoryService.getCategoryById(Long.valueOf(id));
    }
}
