package com.task.springshop.service.impl;

import com.task.springshop.repository.jdbc.ProductRepository;
import com.task.springshop.service.CartProductService;
import com.task.springshop.util.CartProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartProductServiceImpl implements CartProductService {
    private final ProductRepository productRepository;

    @Override
    public void addProduct(CartProducts productsInCart, long productId) {
        if (productsInCart.containsKey(productId)) {
            int quantity = productsInCart.get(productId);
            productsInCart.replace(productId, quantity + 1);
        } else {
            productsInCart.put(productId, 1);
        }
    }

    @Override
    public void removeProduct(CartProducts productsInCart, long productId) {
        if (productsInCart.containsKey(productId)) {
            int quantity = productsInCart.get(productId);

            if (quantity > 1) {
                productsInCart.replace(productId, quantity - 1);
            }
            else if (quantity == 1){
                productsInCart.remove(productId);
            }
        }
    }

    @Override
    public BigDecimal calculateOrderPrice(CartProducts productsInCart, String receivingWay) {
        BigDecimal result = BigDecimal.ZERO;
        Set<Long> productIdSet = productsInCart.keySet();

        for (Long id : productIdSet) {
            Optional<BigDecimal> price = productRepository.findPriceByProductId(id);

            if (price.isPresent()) {
                BigDecimal quantity = BigDecimal.valueOf(productsInCart.get(id));
                result = result.add(price.get().multiply(quantity));
            }
        }

        if (receivingWay.equals("Доставка (5.00 руб.)")) {
            result = result.add(BigDecimal.valueOf(5));
        }

        log.debug("Order's price: {}", result);

        return result;
    }
}