package com.kafkalearning.orderservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public PaymentConsumer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(
            topics = "order-events-demo",
            groupId = "payment-service"
    )
    public void consume(
            ConsumerRecord<String, OrderCreatedEvent> record,
            Acknowledgment acknowledgment
    ) throws Exception {

        OrderCreatedEvent event = record.value();

        System.out.println(
                "Payment processing for " + event.orderId()
        );

        kafkaTemplate
                .send(
                        "payment-events-demo",
                        event.orderId(),
                        event
                )
                .get();

        System.out.println(
                "PaymentCompleted published for " + event.orderId()
        );

        // 💥 We'll add the failure here in the next step.

//        if (true) {
//            throw new RuntimeException("💥 Crash after payment event published");
//        }

        acknowledgment.acknowledge();

        System.out.println(
                "Order event acknowledged for " + event.orderId()
        );
    }
}