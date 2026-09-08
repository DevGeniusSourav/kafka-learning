package com.kafkalearning.orderservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderConsumer {

    private final Set<String> processedEvents = new HashSet<>();

    @KafkaListener(
            topics = "order-events-demo",
            groupId = "notification-service"
    )
    public void consume(
            ConsumerRecord<String, OrderCreatedEvent> record,
            Acknowledgment acknowledgment
    ) {
        OrderCreatedEvent event = record.value();

        String eventId = event.orderId() + "-" + event.status();

        if ("order-307".equals(event.orderId())) {
            throw new RuntimeException("Simulated processing failure");
        }

        System.out.println("Sending notification for " + event.orderId());

        processedEvents.add(eventId);

        System.out.println("Event marked as processed: " + eventId);

        acknowledgment.acknowledge();
    }
}