package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.model.Product;
import com.inventory.inventory_management.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ProductRepository productRepository;

    public InventoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{sku}")
    public Product getBySku(@PathVariable String sku) {
        return productRepository.findById(sku)
                .orElseThrow(() -> new RuntimeException("Product not found: " + sku));
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{sku}")
    public Product update(@PathVariable String sku, @RequestBody Product updated) {
        Product existing = productRepository.findById(sku)
                .orElseThrow(() -> new RuntimeException("Product not found: " + sku));

        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setPrice(updated.getPrice());
        existing.setUnit(updated.getUnit());

        return productRepository.save(existing);
    }

    @DeleteMapping("/{sku}")
    public void delete(@PathVariable String sku) {
        productRepository.deleteById(sku);
    }
}
