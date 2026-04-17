package com.example.sqlindexingbenchmark.service;

import com.example.sqlindexingbenchmark.entity.OrderTbl;
import com.example.sqlindexingbenchmark.repository.OrderRepository;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final int BATCH_SIZE = 1000;

    private final Faker faker = new Faker();

    @Transactional
    public void generateAllOrders(int totalOrders) {
        List<OrderTbl> batch = new ArrayList<>();

        for(int i=0; i<totalOrders; i++) {
            OrderTbl orderTbl = new OrderTbl();
            orderTbl.setOrderId("ORD" + UUID.randomUUID());
            orderTbl.setProductName(faker.commerce().productName());
            orderTbl.setPrice(ThreadLocalRandom.current().nextInt(100, 50000));
            batch.add(orderTbl);

            if (batch.size() == BATCH_SIZE) {
                orderRepository.saveAll(batch);
                // memory optimization
                entityManager.flush();
                entityManager.clear();
                batch.clear();
                System.out.println("Inserted: " + i);
            }
        }
        // Remaining records
        if (!batch.isEmpty()) {
            orderRepository.saveAll(batch);
            entityManager.flush();
            entityManager.clear();
        }
    }

    public Optional<OrderTbl> findOrderIdById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderTbl findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
}
