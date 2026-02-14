package com.sliit.product_service.service;

import com.sliit.product_service.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // save a product
    Product save(Product product);

    // get all products
    List<Product> findAll();

    // get a product by id
    Optional<Product> findById(Long id);

    // delete a product by id
    void deleteById(Long id);

    // check if a product exists by id
    boolean existsById(Long id);
}

