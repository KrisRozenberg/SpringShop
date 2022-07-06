package com.task.springshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

    @Column(nullable = false, length = 25)
    private String login;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 35)
    private String email;

    @Column(nullable = false, columnDefinition = "enum('ADMIN','CLIENT')")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, columnDefinition = "enum('ACTIVE','BLOCKED')")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "sum_spended")
    private BigDecimal sumSpended;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User(String name, String surname, String login, String password, String email, Role role, UserStatus userStatus, BigDecimal sumSpended, Discount discount) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
        this.sumSpended = sumSpended;
        this.discount = discount;
    }

    public User(String name, String surname, String login, String password, String email, Role role, UserStatus userStatus) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
    }
}
