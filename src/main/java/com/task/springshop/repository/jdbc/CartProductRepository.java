package com.task.springshop.repository.jdbc;

import com.task.springshop.entity.CartProduct;

public interface CartProductRepository {
    long saveUnsigned(CartProduct cartProduct);
}
