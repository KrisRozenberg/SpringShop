package com.task.springshop.service.impl;

import com.task.springshop.entity.*;
import com.task.springshop.repository.jdbc.CartProductRepository;
import com.task.springshop.repository.jdbc.OrderRepository;
import com.task.springshop.repository.jdbc.ProductRepository;
import com.task.springshop.service.OrderService;
import com.task.springshop.util.CartProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void addUnsignedOrder(CartProducts productsInCart, BigDecimal price, String receivingWay, String name,
                                 String surname, String address) {
        if (!receivingWay.equals("Доставка (5.00 руб.)")) {
            address = receivingWay;
        }

        Order order = new Order(LocalDate.now(), price, address, OrderStatus.IN_PROCESS, name, surname);

        log.debug("New order: {}", order);

        long orderId = orderRepository.save(order);
        Set<Long> productIdSet = productsInCart.keySet();

        productIdSet.forEach(id -> {
            Optional<Product> product = productRepository.findById(id);
            Optional<BigDecimal> finalPrice = productRepository.findPriceByProductId(id);
            if (product.isPresent() && finalPrice.isPresent()) {
                CartProduct cartProduct = new CartProduct(productsInCart.get(id), CartProductStatus.OUT_OF_CART,
                        finalPrice.get(), product.get().getProductId(), orderId);
                cartProductRepository.saveUnsigned(cartProduct);
            }
        });
    }
}
