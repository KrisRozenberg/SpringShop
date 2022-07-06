package com.task.springshop.service;

import com.task.springshop.entity.Discount;

import java.util.Optional;

public interface DiscountService {
    void addDiscount(Discount discount);
    Optional<Discount> findById(Long id);
}
