package com.example.moneyloveroperationservice.repository;

import com.example.moneyloveroperationservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
