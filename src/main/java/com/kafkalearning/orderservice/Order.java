package com.kafkalearning.orderservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private String status;

    private Double amount;

    protected Order() {
    }

    public Order(String orderId, String status, Double amount) {
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public Double getAmount() {
        return amount;
    }
}