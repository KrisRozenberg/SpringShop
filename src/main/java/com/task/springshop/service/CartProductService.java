package com.task.springshop.service;

import com.task.springshop.util.CartProducts;

import java.math.BigDecimal;

public interface CartProductService {
    void addProduct(CartProducts productsInCart, long productId);
    void removeProduct(CartProducts productsInCart, long productId);
    BigDecimal calculateOrderPrice(CartProducts productsInCart, String receivingWay);
}
