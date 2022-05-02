package com.task.springshop.controller;

import com.task.springshop.entity.Order;
import com.task.springshop.entity.Product;
import com.task.springshop.service.CartProductService;
import com.task.springshop.service.OrderService;
import com.task.springshop.service.ProductService;
import com.task.springshop.util.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ResourceController {
    private final ProductService productService;
    private final CartProductService cartProductService;
    private final OrderService orderService;

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = productService.findAll();

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/makeOrder")
    public ResponseEntity<Order> makeOrder(@Valid @RequestBody OrderInfo orderInfo) {
        BigDecimal price = cartProductService.calculateOrderPrice(orderInfo.getProductQuantity(), orderInfo.getReceivingWay());
        Order order = orderService.addUnsignedOrder(orderInfo, price);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
