package com.task.springshop.service;

import com.task.springshop.entity.*;
import com.task.springshop.repository.CartProductRepository;
import com.task.springshop.repository.OrderRepository;
import com.task.springshop.repository.ProductRepository;
import com.task.springshop.util.CartProducts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addUnsignedOrder(CartProducts productsInCart, BigDecimal price, String receivingWay, String name,
                                 String surname, String address) {
        if (!receivingWay.equals("Доставка (5.00 руб.)")) {
            address = receivingWay;
        }
        Order order = new Order(LocalDate.now(), price, address, OrderStatus.IN_PROCESS, name, surname, new HashSet<>());
        logger.debug("New order: {}", order);
        orderRepository.save(order);
        Set<Long> productId = productsInCart.keySet();
        productId.forEach(id -> {
            Optional<Product> product = productRepository.findById(id);
            Optional<BigDecimal> finalPrice = productRepository.findPriceByProductId(id);
            if (product.isPresent() && finalPrice.isPresent()) {
                CartProduct cartProduct = new CartProduct(productsInCart.get(id), CartProductStatus.OUT_OF_CART,
                        finalPrice.get(), product.get(), order);
                cartProductRepository.save(cartProduct);
            }
        });
    }
}
