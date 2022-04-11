package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    private Long discountId;
    private BigDecimal discount;
    private int sumSpended;
}
