package com.task.springshop.entity;

import com.task.springshop.validator.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
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
    @Size(max = 100, message = "Address must contain less then 100 characters. ")
    private String address;

    @Column(nullable = false, columnDefinition = "enum('IN_PROCESS','DONE', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 30)
    @Size(max = 30, message = "Name must contain less then 30 characters. ")
    private String name;

    @Column(length = 30)
    @Size(max = 30, message = "Surname must contain less then 30 characters. ")
    private String surname;

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
