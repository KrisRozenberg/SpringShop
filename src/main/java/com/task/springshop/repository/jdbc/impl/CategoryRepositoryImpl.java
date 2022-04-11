package com.task.springshop.repository.jdbc.impl;

import com.task.springshop.entity.Category;
import com.task.springshop.repository.jdbc.CategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_QUERY = """
            INSERT INTO categories (name, parent_category_id)
            VALUES (?, ?);""";

    private static final String SAVE_PARENT_QUERY = """
            INSERT INTO categories (name)
            VALUES (?);""";

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Category category) {
        if (category.getParentCategoryId() != 0) {
            return jdbcTemplate.update(SAVE_QUERY, category.getName(), category.getParentCategoryId());
        } else {
            return jdbcTemplate.update(SAVE_PARENT_QUERY, category.getName());
        }

    }
}

