package com.kafkalearning.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public String createOrder(
            @RequestParam String orderId,
            @RequestParam Double amount
    ) throws JsonProcessingException {

        orderService.createOrder(orderId, amount);

        return "Order created";
    }
}