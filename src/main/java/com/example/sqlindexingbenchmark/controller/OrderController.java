package com.example.sqlindexingbenchmark.controller;

import com.example.sqlindexingbenchmark.entity.OrderTbl;
import com.example.sqlindexingbenchmark.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/generate/{totalOrders}")
    public String generateOrders(@PathVariable int totalOrders) {
        ordersService.generateAllOrders(totalOrders);
        return totalOrders + "Record Generated";
    }
    
    @GetMapping("/findOrderId/{id}")
    public Optional<OrderTbl> findOrderId(@PathVariable Long id) {
        return ordersService.findOrderIdById(id);
    }

    @GetMapping("/findByOrderId/{orderId}")
    public OrderTbl checkOrder(@PathVariable String orderId) {
        return ordersService.findByOrderId(orderId);
    }
}
