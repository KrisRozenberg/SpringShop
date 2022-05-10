package com.task.springshop.service;

import com.task.springshop.util.OrderInfo;

import java.math.BigDecimal;

public interface OrderService {
    void addUnsignedOrder(OrderInfo orderInfo, BigDecimal price);
}
