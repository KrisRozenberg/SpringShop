package com.task.springshop.controller;

import com.task.springshop.entity.Order;
import com.task.springshop.entity.OrderStatus;
import com.task.springshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final OrderService orderService;

    @GetMapping("/getOrderList")
    public ResponseEntity<List<Order>> getOrderList() {
        List<Order> orderList = orderService.findAll();

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/completeOrder")
    public  ResponseEntity<?> completeOrder(@RequestParam("id") long id) {
        orderService.updateStatusById(id, OrderStatus.DONE);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
