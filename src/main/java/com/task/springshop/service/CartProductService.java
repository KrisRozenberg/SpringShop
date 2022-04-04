package com.task.springshop.service;

import com.task.springshop.repository.ProductRepository;
import com.task.springshop.util.CartProducts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartProductService {
    private static final Logger logger = LoggerFactory.getLogger(CartProductService.class);
    private final ProductRepository productRepository;

    public void addProduct(CartProducts productsInCart, long productId) {
        if (productsInCart.containsKey(productId)) {
            int quantity = productsInCart.get(productId);
            productsInCart.replace(productId, quantity + 1);
        }
        else {
            productsInCart.put(productId, 1);
        }
    }

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

    public BigDecimal calculateOrderPrice(CartProducts productsInCart, String receivingWay) {
        BigDecimal result = BigDecimal.ZERO;
        Set<Long> productId = productsInCart.keySet();

        for (Long id : productId) {
            Optional<BigDecimal> price = productRepository.findPriceByProductId(id);
            if (price.isPresent()) {
                BigDecimal quantity = BigDecimal.valueOf(productsInCart.get(id));
                result = result.add(price.get().multiply(quantity));
            }
        }
        if (receivingWay.equals("Доставка (5.00 руб.)")) {
            result = result.add(BigDecimal.valueOf(5));
        }
        logger.debug("Order's price: {}", result);

        return result;
    }
}
