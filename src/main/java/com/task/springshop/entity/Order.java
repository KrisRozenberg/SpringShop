package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long orderId;
    private LocalDate date;
    private BigDecimal price;
    @Size(max = 100, message = "Address must contain less then 100 characters. ")
    private String address;
    private OrderStatus status;
    @Size(max = 30, message = "Name must contain less then 30 characters. ")
    private String name;
    @Size(max = 30, message = "Surname must contain less then 30 characters. ")
    private String surname;

    public Order(LocalDate date, BigDecimal price, String address, OrderStatus status, String name, String surname) {
        this.date = date;
        this.price = price;
        this.address = address;
        this.status = status;
        this.name = name;
        this.surname = surname;
    }
}
