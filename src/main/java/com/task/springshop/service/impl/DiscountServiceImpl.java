package com.task.springshop.service.impl;

import com.task.springshop.entity.Discount;
import com.task.springshop.repository.DiscountRepository;
import com.task.springshop.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    @Override
    public void addDiscount(Discount discount) {
        //consistency checking

        discountRepository.save(discount);
    }

    @Override
    public Optional<Discount> findById(Long id) {

        return discountRepository.findById(id);
    }

}
