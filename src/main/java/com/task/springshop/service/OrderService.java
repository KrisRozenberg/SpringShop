package com.task.springshop.service;

import com.task.springshop.entity.Order;
import com.task.springshop.util.OrderInfo;

import java.math.BigDecimal;

public interface OrderService {
    Order addUnsignedOrder(OrderInfo orderInfo, BigDecimal price);
}
