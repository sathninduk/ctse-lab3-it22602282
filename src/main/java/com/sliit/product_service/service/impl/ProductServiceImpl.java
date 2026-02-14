package com.sliit.product_service.service.impl;

import com.sliit.product_service.entity.Product;
import com.sliit.product_service.repository.ProductRepository;
import com.sliit.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    // save product
    @Override
    public Product save(Product product) {
        try {
            return repository.save(product);
        } catch (Exception e) {
            log.error("Error saving product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save product", e);
        }
    }

    // find all products
    @Override
    public List<Product> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("Error fetching products: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch products", e);
        }
    }

    // find product by id
    @Override
    public Optional<Product> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            log.error("Error fetching product by id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch product by id " + id, e);
        }
    }

    // delete product by id
    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting product by id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete product by id " + id, e);
        }
    }

    // Check if product exists by id
    @Override
    public boolean existsById(Long id) {
        try {
            return repository.existsById(id);
        } catch (Exception e) {
            log.error("Error checking existence of product by id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to check existence of product by id " + id, e);
        }
    }
}

