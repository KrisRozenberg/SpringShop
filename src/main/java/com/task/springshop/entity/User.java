package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private Role role;
    private UserStatus userStatus;
    private BigDecimal sumSpended;
    private Long discountId;
    private Long cartId;
}
