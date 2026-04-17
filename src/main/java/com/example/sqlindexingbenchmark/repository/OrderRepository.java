package com.example.sqlindexingbenchmark.repository;

import com.example.sqlindexingbenchmark.entity.OrderTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderTbl, Long> {

    Optional<OrderTbl> findByOrderId(String orderId);
}
