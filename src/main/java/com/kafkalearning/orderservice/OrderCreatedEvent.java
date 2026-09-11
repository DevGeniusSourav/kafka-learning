package com.kafkalearning.orderservice;

public record OrderCreatedEvent(
        String eventId,
        String orderId,
        String status,
        Double amount
) {
}