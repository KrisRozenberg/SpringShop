package com.task.springshop.service.impl;

import com.task.springshop.entity.Product;
import com.task.springshop.repository.jdbc.ProductRepository;
import com.task.springshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {

        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public Optional<Integer> findQuantityByProductId(Long id) {

        return productRepository.findQuantityByProductId(id);
    }
}
