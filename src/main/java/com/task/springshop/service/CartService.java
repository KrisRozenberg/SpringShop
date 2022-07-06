package com.task.springshop.service;

import com.task.springshop.entity.Cart;
import com.task.springshop.entity.User;

import java.util.Optional;

public interface CartService {
    void addCart(Cart cart);

    Optional<Cart> findCartByUser(User user);
}
