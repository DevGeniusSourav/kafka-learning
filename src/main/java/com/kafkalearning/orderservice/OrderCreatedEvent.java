package com.kafkalearning.orderservice;

public record OrderCreatedEvent(
        String orderId,
        String status
) {
}