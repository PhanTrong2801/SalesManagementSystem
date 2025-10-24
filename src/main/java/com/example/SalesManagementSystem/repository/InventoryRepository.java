package com.example.SalesManagementSystem.repository;

import com.example.SalesManagementSystem.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

}
