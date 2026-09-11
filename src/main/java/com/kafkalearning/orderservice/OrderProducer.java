//package com.kafkalearning.orderservice;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderProducer {
//
//    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
//
//    public OrderProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void publishOrderCreated(String orderId) {
//        OrderCreatedEvent event = new OrderCreatedEvent(
//                orderId,
//                "created",
//                1500.0
//        );
//
//        kafkaTemplate
//                .send("order-events-demo", orderId, event)
//                .whenComplete((result, ex) -> {
//
//                    if (ex != null) {
//                        System.out.println("FAILED TO SEND EVENT");
//                        ex.printStackTrace();
//                    } else {
//                        System.out.println(
//                                "EVENT SENT TO PARTITION "
//                                        + result.getRecordMetadata().partition()
//                                        + " OFFSET "
//                                        + result.getRecordMetadata().offset()
//                        );
//                    }
//                });
//    }
//}