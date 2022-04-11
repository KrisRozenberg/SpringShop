package com.task.springshop.repository.jdbc.impl;

import com.task.springshop.entity.CartProduct;
import com.task.springshop.repository.jdbc.CartProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class CartProductRepositoryImpl implements CartProductRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_UNSIGNED_QUERY = """
            INSERT INTO cart_products (final_price, quantity, status, order_id, product_id) 
            VALUES (?, ?, ?, ?, ?);""";

    public CartProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long saveUnsigned(CartProduct cartProduct) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE_UNSIGNED_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, cartProduct.getFinalPrice());
            ps.setInt(2, cartProduct.getQuantity());
            ps.setString(3, cartProduct.getStatus().name());
            ps.setLong(4, cartProduct.getOrderId());
            ps.setLong(5, cartProduct.getProductId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
