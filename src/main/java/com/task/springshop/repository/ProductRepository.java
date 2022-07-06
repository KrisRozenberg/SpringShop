package com.task.springshop.repository;

import com.task.springshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.price FROM Product p WHERE p.productId = :productId")
    Optional<BigDecimal> findPriceByProductId(@Param("productId") Long id);

    @Query("SELECT p.quantity FROM Product p WHERE p.productId = :productId")
    Optional<Integer> findQuantityByProductId(@Param("productId") Long id);

    @Modifying
    @Query("UPDATE Product p set p.imageUrl = :imageUrl WHERE p.productId = :productId")
    void updateImageUrlById(@Param("productId") Long id, @Param("imageUrl") String imageUrl);
}
