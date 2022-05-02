package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_products")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Long cartProductId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, columnDefinition = "enum('IN_CART','OUT_OF_CART')")
    @Enumerated(EnumType.STRING)
    private CartProductStatus status;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public CartProduct(Integer quantity, CartProductStatus status, Cart cart, Product product) {
        this.quantity = quantity;
        this.status = status;
        this.cart = cart;
        this.product = product;
    }

    public CartProduct(Integer quantity, CartProductStatus status, BigDecimal finalPrice, Product product, Order order) {
        this.quantity = quantity;
        this.status = status;
        this.finalPrice = finalPrice;
        this.product = product;
        this.order = order;
    }
}
