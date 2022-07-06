package com.task.springshop.service;

import com.task.springshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);

    List<Product> findAll();

    Optional<Integer> findQuantityByProductId(Long id);

    void updateImageUrlById(Long id, String imageUrl);
}
