package com.task.springshop.service;

import com.task.springshop.entity.Product;
import com.task.springshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Integer> findQuantityByProductId(Long id) {
        return productRepository.findQuantityByProductId(id);
    }
}
