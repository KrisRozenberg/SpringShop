package com.task.springshop.repository.jdbc;

import com.task.springshop.entity.Category;

public interface CategoryRepository {
    int save(Category category);
}
