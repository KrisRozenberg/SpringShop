package com.task.springshop.service;

import com.task.springshop.util.CartProducts;

import java.math.BigDecimal;

public interface OrderService {
    void addUnsignedOrder(CartProducts productsInCart, BigDecimal price, String receivingWay, String name,
                                 String surname, String address);
}
