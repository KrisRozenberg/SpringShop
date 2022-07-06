package com.task.springshop.service.impl;

import com.task.springshop.entity.*;
import com.task.springshop.repository.CartProductRepository;
import com.task.springshop.repository.CartRepository;
import com.task.springshop.repository.OrderRepository;
import com.task.springshop.repository.ProductRepository;
import com.task.springshop.service.OrderService;
import com.task.springshop.util.SignedInOrderInfo;
import com.task.springshop.util.UnsignedOrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public void addUnsignedOrder(UnsignedOrderInfo unsignedOrderInfo, BigDecimal price) {
        Order order = new Order(LocalDate.now(), price, unsignedOrderInfo.getAddress(), OrderStatus.IN_PROCESS, unsignedOrderInfo.getName(),
                unsignedOrderInfo.getSurname(), new HashSet<>());

        log.debug("New order: {}", order);

        orderRepository.save(order);
        Set<Long> productId = unsignedOrderInfo.getProductQuantity().keySet();

        productId.forEach(id -> {
            Optional<Product> product = productRepository.findById(id);
            Optional<BigDecimal> finalPrice = productRepository.findPriceByProductId(id);

            if (product.isPresent() && finalPrice.isPresent()) {
                CartProduct cartProduct = new CartProduct(unsignedOrderInfo.getProductQuantity().get(id), CartProductStatus.OUT_OF_CART,
                        finalPrice.get(), product.get(), order);
                cartProductRepository.save(cartProduct);
            }
        });
    }

    @Override
    @Transactional
    public void addSignedInOrder(SignedInOrderInfo signedInOrderInfo, User user, BigDecimal price) {
        Order order = new Order(LocalDate.now(), price, signedInOrderInfo.getAddress(), OrderStatus.IN_PROCESS, user.getName(),
                user.getSurname(), new HashSet<>());

        log.debug("New order: {}", order);

        orderRepository.save(order);
        Optional<Cart> cart = cartRepository.findCartByUser(user);

        if (cart.isPresent()) {
            Set<Long> productId = signedInOrderInfo.getProductQuantity().keySet();

            productId.forEach(id -> {
                Optional<Product> product = productRepository.findById(id);
                Optional<BigDecimal> finalPrice = productRepository.findPriceByProductId(id);

                if (product.isPresent() && finalPrice.isPresent()) {
                    CartProduct cartProduct = new CartProduct(signedInOrderInfo.getProductQuantity().get(id), CartProductStatus.OUT_OF_CART,
                            finalPrice.get(), cart.get(), product.get(), order);
                    cartProductRepository.save(cartProduct);
                }
            });
        }
    }

    @Override
    public List<Order> findAll() {

        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void updateStatusById(Long id, OrderStatus status) {

         orderRepository.updateStatusById(id, status);
    }

    @Transactional
    @Override
    public List<Order> findClientOrders(User user) {
        List<Order> clientOrders = new ArrayList<>();
        Optional<Cart> cart = this.cartRepository.findCartByUser(user);

        if (cart.isPresent()) {
            clientOrders = cartProductRepository.findOrdersByCart(cart.get());
        }

        List<Order> resultOrders = new ArrayList<>();
        clientOrders.forEach(order -> {
            if (!resultOrders.contains(order)) {
                resultOrders.add(order);
            }
        });

        return resultOrders;
    }
}

