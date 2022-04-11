package com.task.springshop.repository.jdbc;

import com.task.springshop.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<BigDecimal> findPriceByProductId(Long id);
    Optional<Integer> findQuantityByProductId(Long id);
    int save(Product product);
}
