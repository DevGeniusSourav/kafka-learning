package com.kafkalearning.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    public OrderService(
            OrderRepository orderRepository,
            OutboxEventRepository outboxEventRepository,
            ObjectMapper objectMapper
    ) {
        this.orderRepository = orderRepository;
        this.outboxEventRepository = outboxEventRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional("transactionManager")
    public void createOrder(
            String orderId,
            Double amount
    ) throws JsonProcessingException {

        Order order = new Order(
                orderId,
                "created",
                amount
        );

        orderRepository.save(order);

        String eventId = UUID.randomUUID().toString();

        OrderCreatedEvent event = new OrderCreatedEvent(
                eventId,
                orderId,
                "created",
                amount
        );

        String payload = objectMapper.writeValueAsString(event);

        OutboxEvent outboxEvent = new OutboxEvent(
                eventId,
                "OrderCreated",
                payload
        );

        outboxEventRepository.save(outboxEvent);

        System.out.println(
                "Order + Outbox event saved. Event ID: " + eventId
        );
    }
}