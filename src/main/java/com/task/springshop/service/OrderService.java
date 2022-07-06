package com.task.springshop.service;

import com.task.springshop.entity.Order;
import com.task.springshop.entity.OrderStatus;
import com.task.springshop.entity.User;
import com.task.springshop.util.SignedInOrderInfo;
import com.task.springshop.util.UnsignedOrderInfo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void addUnsignedOrder(UnsignedOrderInfo unsignedOrderInfo, BigDecimal price);

    void addSignedInOrder(SignedInOrderInfo signedInOrderInfo, User user, BigDecimal price);

    List<Order> findAll();

    void updateStatusById(Long id, OrderStatus status);

    List<Order> findClientOrders(User user);
}
