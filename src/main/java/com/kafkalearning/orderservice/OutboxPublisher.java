package com.kafkalearning.orderservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxPublisher {

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OutboxPublisher(
            OutboxEventRepository outboxEventRepository,
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate
    ) {
        this.outboxEventRepository = outboxEventRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void publishPendingEvents() {

        for (OutboxEvent outboxEvent : outboxEventRepository.findByPublishedFalse()) {

            try {
                OrderCreatedEvent event =
                        new com.fasterxml.jackson.databind.ObjectMapper()
                                .readValue(
                                        outboxEvent.getPayload(),
                                        OrderCreatedEvent.class
                                );

                kafkaTemplate
                        .send(
                                "order-events-demo",
                                event.orderId(),
                                event
                        )
                        .get();

//                if (true) {
//                    throw new RuntimeException("💥 Simulated crash after Kafka send");
//                }

                outboxEvent.markPublished();
                outboxEventRepository.save(outboxEvent);

                System.out.println(
                        "Outbox event published: " + outboxEvent.getId()
                );

            } catch (Exception e) {
                System.out.println(
                        "Failed to publish outbox event: "
                                + outboxEvent.getId()
                );
            }
        }
    }
}