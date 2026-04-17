package com.example.sqlindexingbenchmark.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "order_with_index",
        indexes = {
                @Index(name = "idx_order_id", columnList = "orderId")
        }
)
public class OrderTbl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private String productName;

    private int price;

    public OrderTbl() {
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName(String s) {
        return productName;
    }

    public OrderTbl(Long id, String orderId, String productName, int price) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
