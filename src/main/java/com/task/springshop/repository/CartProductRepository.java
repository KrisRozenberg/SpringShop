package com.task.springshop.repository;

import com.task.springshop.entity.Cart;
import com.task.springshop.entity.CartProduct;
import com.task.springshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @Query("SELECT cp.order FROM CartProduct cp WHERE cp.cart = :cart")
    List<Order> findOrdersByCart(@Param("cart") Cart cart);
}
