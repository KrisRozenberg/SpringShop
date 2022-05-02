package com.task.springshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "enum('IN_STOCK','OUT_OF_STOCK')")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy="product")
    private Set<CartProduct> cartProducts;

    public Product(String name, String description, String imageUrl, int quantity, BigDecimal price, ProductStatus status, Category category, Set<CartProduct> cartProducts) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.category = category;
        this.cartProducts = cartProducts;
    }
}
