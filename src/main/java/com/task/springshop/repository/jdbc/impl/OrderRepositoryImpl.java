package com.task.springshop.repository.jdbc.impl;

import com.task.springshop.entity.Order;
import com.task.springshop.repository.jdbc.OrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_QUERY = """
            INSERT INTO orders (address, date, name, surname, price, status) 
            VALUES (?, ?, ?, ?, ?, ?);""";

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getAddress());
            ps.setDate(2, Date.valueOf(order.getDate()));
            ps.setString(3, order.getName());
            ps.setString(4, order.getSurname());
            ps.setBigDecimal(5, order.getPrice());
            ps.setString(6, order.getStatus().name());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}