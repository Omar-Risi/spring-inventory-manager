package com.inventory.inventory_management.repository;

import com.inventory.inventory_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
