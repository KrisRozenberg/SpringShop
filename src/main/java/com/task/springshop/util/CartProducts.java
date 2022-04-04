package com.task.springshop.util;

import com.task.springshop.validator.ProductQuantity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartProducts {
    @ProductQuantity
    private Map<Long, Integer> productQuantity;

    public CartProducts() {
        productQuantity = new HashMap<>();
    }

    public Map<Long, Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Map<Long, Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer get(Long key) {
        return productQuantity.get(key);
    }

    public void put(Long key, Integer value) {
        productQuantity.put(key, value);
    }

    public void remove(Long key) {
        productQuantity.remove(key);
    }

    public Integer replace(Long key, Integer value) {
        return productQuantity.replace(key, value);
    }

    public void clear() {
        productQuantity.clear();
    }

    public boolean containsKey(Long key) {
        return productQuantity.containsKey( key);
    }

    public Set<Long> keySet() {
        return productQuantity.keySet();
    }
}
