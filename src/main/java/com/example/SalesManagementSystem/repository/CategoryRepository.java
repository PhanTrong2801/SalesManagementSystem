package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
