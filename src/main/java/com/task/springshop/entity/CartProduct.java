package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    private Long cartProductId;
    private Integer quantity;
    private CartProductStatus status;
    private BigDecimal finalPrice;
    private Long cartId;
    private Long productId;
    private Long orderId;

    public CartProduct(Integer quantity, CartProductStatus status, BigDecimal finalPrice, Long productId, Long orderId) {
        this.quantity = quantity;
        this.status = status;
        this.finalPrice = finalPrice;
        this.productId = productId;
        this.orderId = orderId;
    }
}
