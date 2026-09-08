package com.kafkalearning.orderservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(String orderId) {
        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                "created"
        );

        kafkaTemplate.send("order-events-demo", orderId, event);
    }
}