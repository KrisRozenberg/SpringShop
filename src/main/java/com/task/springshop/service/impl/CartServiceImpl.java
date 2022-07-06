package com.task.springshop.service.impl;

import com.task.springshop.entity.Cart;
import com.task.springshop.entity.User;
import com.task.springshop.repository.CartRepository;
import com.task.springshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void addCart(Cart cart) {

        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findCartByUser(User user) {

        return cartRepository.findCartByUser(user);
    }
}
