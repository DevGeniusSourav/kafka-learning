package com.kafkalearning.orderservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OutboxEvent {

    @Id
    private String id;

    private String eventType;

    private String payload;

    private boolean published;

    protected OutboxEvent() {
    }

    public OutboxEvent(
            String id,
            String eventType,
            String payload
    ) {
        this.id = id;
        this.eventType = eventType;
        this.payload = payload;
        this.published = false;
    }

    public String getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }

    public boolean isPublished() {
        return published;
    }

    public void markPublished() {
        this.published = true;
    }
}