package com.task.springshop.service.impl;

import com.task.springshop.entity.Category;
import com.task.springshop.repository.jdbc.CategoryRepository;
import com.task.springshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

//    @Transactional
//    public Category addCategory(Category category) {
//        if (category.getParentCategory() != null) {
//            category.setParentCategory(addCategory(category.getParentCategory()));
//        }
//        Optional<Category> optionalCategory = categoryRepository.findOneByName(category.getName());
//        if (optionalCategory.isEmpty()) {
//            categoryRepository.save(category);
//            return category;
//        }
//        else {
//            return optionalCategory.get();
//        }
//    }

    @Override
    public void addCategory(Category category) {

        categoryRepository.save(category);
    }
}
