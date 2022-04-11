package com.task.springshop.repository.jdbc;

import com.task.springshop.entity.Order;

public interface OrderRepository {
    long save(Order order);
}
