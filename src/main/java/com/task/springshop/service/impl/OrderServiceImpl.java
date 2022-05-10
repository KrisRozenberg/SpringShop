package com.task.springshop.service.impl;

import com.task.springshop.entity.*;
import com.task.springshop.repository.CartProductRepository;
import com.task.springshop.repository.OrderRepository;
import com.task.springshop.repository.ProductRepository;
import com.task.springshop.service.OrderService;
import com.task.springshop.util.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void addUnsignedOrder(OrderInfo orderInfo, BigDecimal price) {
        Order order = new Order(LocalDate.now(), price, orderInfo.getAddress(), OrderStatus.IN_PROCESS, orderInfo.getName(),
                orderInfo.getSurname(), new HashSet<>());

        log.debug("New order: {}", order);

        orderRepository.save(order);
        Set<Long> productId = orderInfo.getProductQuantity().keySet();

        productId.forEach(id -> {
            Optional<Product> product = productRepository.findById(id);
            Optional<BigDecimal> finalPrice = productRepository.findPriceByProductId(id);

            if (product.isPresent() && finalPrice.isPresent()) {
                CartProduct cartProduct = new CartProduct(orderInfo.getProductQuantity().get(id), CartProductStatus.OUT_OF_CART,
                        finalPrice.get(), product.get(), order);
                cartProductRepository.save(cartProduct);
            }
        });
    }
}

