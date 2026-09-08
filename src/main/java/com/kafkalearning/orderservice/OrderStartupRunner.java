package com.kafkalearning.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderStartupRunner implements CommandLineRunner {

    private final OrderProducer orderProducer;

    public OrderStartupRunner(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Override
    public void run(String... args) {
        orderProducer.publishOrderCreated("order-307");
//        orderProducer.publishOrderCreated("order-306");
//        orderProducer.publishOrderCreated("order-111");
//        orderProducer.publishOrderCreated("order-112");
//        orderProducer.publishOrderCreated("order-113");
//        orderProducer.publishOrderCreated("order-114");
//        orderProducer.publishOrderCreated("order-115");
//        orderProducer.publishOrderCreated("order-116");
//        orderProducer.publishOrderCreated("order-117");
//        orderProducer.publishOrderCreated("order-118");
//        orderProducer.publishOrderCreated("order-119");
//        orderProducer.publishOrderCreated("order-120");
    }
}