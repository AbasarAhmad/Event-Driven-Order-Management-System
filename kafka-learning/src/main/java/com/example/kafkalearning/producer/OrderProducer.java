
package com.example.kafkalearning.producer;

import com.example.kafkalearning.event.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducer {

    private static final String TOPIC = "order-events";

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, OrderEvent>>
            sendOrderEvent(OrderEvent orderEvent) {

        return kafkaTemplate.send(
                TOPIC,
                orderEvent.getOrderId(),
                orderEvent
        );
    }
}

//@Servic - Registers this class as a Spring service
//KafkaTemplate	- Spring Kafka class used to send messages
//String -  Type of the Kafka message key
//OrderEvent	- Type of the message value
//TOPIC	 - Kafka topic where the event will be sent
//send()	- Sends the event to Kafka