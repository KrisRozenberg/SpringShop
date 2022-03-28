package com.task.springshop.formal.repository;

import java.util.HashMap;
import java.util.Map;

public class OrderProductRepository {
    private static OrderProductRepository instance;
    private final Map<Long, Integer> productsInBasket;

    OrderProductRepository() {
        productsInBasket = new HashMap<>(Map.of(
                (long)1, 0,
                (long)2, 0,
                (long)3, 0,
                (long)4, 0,
                (long)5, 0,
                (long)6, 0,
                (long)7, 0,
                (long)8, 0,
                (long)9, 0,
                (long)10, 0
        ));
    }

    public static OrderProductRepository getInstance() {
        if (instance == null) {
            instance = new OrderProductRepository();
        }
        return instance;
    }

    public int get(long key) {
        return productsInBasket.get(key);
    }

    public Map<Long, Integer> getAll() {
        return productsInBasket;
    }

    public int replace(long key, int newValue) {
        return productsInBasket.replace(key, newValue);
    }

    public void clearBasket() {
        productsInBasket.replaceAll((key, value) -> 0);
    }
}
