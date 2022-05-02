package com.task.springshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, columnDefinition = "enum('IN_PROCESS','DONE', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String surname;

    @JsonIgnore
    @OneToMany(mappedBy="order")
    private Set<CartProduct> cartProducts;

    public Order(LocalDate date, BigDecimal price, String address, OrderStatus status, String name, String surname, Set<CartProduct> cartProducts) {
        this.date = date;
        this.price = price;
        this.address = address;
        this.status = status;
        this.name = name;
        this.surname = surname;
        this.cartProducts = cartProducts;
    }
}
