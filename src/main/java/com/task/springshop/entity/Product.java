package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private int quantity;
    private BigDecimal price;
    private ProductStatus status;
    private Long categoryId;

    public Product(String name, String description, String imageUrl, int quantity, BigDecimal price, ProductStatus status, Long categoryId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
    }
}
