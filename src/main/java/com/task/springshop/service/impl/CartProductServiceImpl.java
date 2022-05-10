package com.task.springshop.service.impl;

import com.task.springshop.repository.ProductRepository;
import com.task.springshop.service.CartProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartProductServiceImpl implements CartProductService {
    private final ProductRepository productRepository;

    @Override
    public BigDecimal calculateOrderPrice(Map<Long, Integer> productsInCart, boolean isDelivery) {
        BigDecimal result = BigDecimal.ZERO;
        Set<Long> productId = productsInCart.keySet();

        for (Long id : productId) {
            Optional<BigDecimal> price = productRepository.findPriceByProductId(id);
            if (price.isPresent()) {
                BigDecimal quantity = BigDecimal.valueOf(productsInCart.get(id));
                result = result.add(price.get().multiply(quantity));
            }
        }

        if (isDelivery) {
            result = result.add(BigDecimal.valueOf(5));
        }

        log.debug("Order's price: {}", result);

        return result;
    }
}

