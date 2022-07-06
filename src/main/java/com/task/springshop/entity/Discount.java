package com.task.springshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "discounts")
public class Discount {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long discountId;

    @Column(nullable = false)
    private BigDecimal discount;

    @Column(name = "sum_spended", nullable = false)
    private int sumSpended;

    @OneToMany(mappedBy="discount")
    @JsonIgnore
    private Set<User> users;

    public Discount(BigDecimal discount, int sumSpended, Set<User> users) {
        this.discount = discount;
        this.sumSpended = sumSpended;
        this.users = users;
    }
}
