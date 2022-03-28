package com.task.springshop.formal.repository;

import com.task.springshop.model.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {
    private static OrdersRepository instance;
    private final List<Order> orders;

    OrdersRepository() {
        orders = new ArrayList<>();
    }

    public static OrdersRepository getInstance() {
        if (instance == null) {
            instance = new OrdersRepository();
        }
        return instance;
    }

    public boolean add(Order order) {
        return orders.add(order);
    }

    public int size() {
        return orders.size();
    }
}
