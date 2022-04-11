package com.task.springshop.repository.jdbc.impl;

import com.task.springshop.entity.Product;
import com.task.springshop.repository.jdbc.ProductRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_QUERY = """
            SELECT product_id, description, image_url, name, price, quantity, status, category_id  
            FROM products;""";

    private static final String FIND_BY_ID_QUERY = """
            SELECT product_id, description, image_url, name, price, quantity, status, category_id  
            FROM products
            WHERE product_id = ?;""";

    private static final String FIND_PRICE_BY_PRODUCT_ID_QUERY = """
            SELECT price
            FROM products
            WHERE product_id = ?;""";

    private static final String FIND_QUANTITY_BY_PRODUCT_ID_QUERY = """
            SELECT quantity
            FROM products
            WHERE product_id = ?;""";

    private static final String SAVE_QUERY = """
            INSERT INTO products (name, description, image_url, price, quantity, status, category_id)
            VALUES (?, ?, ?, ?, ?, ?, ?);""";

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {

        return jdbcTemplate.query(FIND_ALL_QUERY, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Optional<Product> findById(Long id) {

        return jdbcTemplate.query(FIND_BY_ID_QUERY, new BeanPropertyRowMapper<>(Product.class), id).stream().findAny();
    }

    @Override
    public Optional<BigDecimal> findPriceByProductId(Long id) {

        return jdbcTemplate.query(FIND_PRICE_BY_PRODUCT_ID_QUERY, rs -> {
            Optional<BigDecimal> price = Optional.empty();
            if (rs.next()) {
                price = Optional.of(rs.getBigDecimal("price"));
            }
            return price;
        }, id);
    }

    @Override
    public Optional<Integer> findQuantityByProductId(Long id) {

        return jdbcTemplate.query(FIND_QUANTITY_BY_PRODUCT_ID_QUERY, rs -> {
            Optional<Integer> quantity = Optional.empty();
            if (rs.next()) {
                quantity = Optional.of(rs.getInt("quantity"));
            }
            return quantity;
        }, id);
    }

    @Override
    public int save(Product product) {

        return jdbcTemplate.update(SAVE_QUERY, product.getName(), product.getDescription(), product.getImageUrl(),
                product.getPrice(), product.getQuantity(), product.getStatus(), product.getCategoryId());
    }
}
