package com.task.springshop.repository;

import com.task.springshop.entity.Order;
import com.task.springshop.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order o set o.status = :status WHERE o.orderId = :orderId")
    void updateStatusById(@Param("orderId") Long id, @Param("status") OrderStatus status);
}
