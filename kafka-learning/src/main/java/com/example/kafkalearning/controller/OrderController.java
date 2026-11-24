
package com.example.kafkalearning.controller;

import com.example.kafkalearning.event.OrderEvent;
import com.example.kafkalearning.producer.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}

	@PostMapping("/publish")
	public CompletableFuture<ResponseEntity<String>> publishOrder(@RequestBody OrderEvent orderEvent) {

		return orderProducer.sendOrderEvent(orderEvent)
				.thenApply(result -> ResponseEntity.ok("Order event published successfully. Partition: "
						+ result.getRecordMetadata().partition() + ", Offset: " + result.getRecordMetadata().offset()))
				.exceptionally(
						ex -> ResponseEntity.internalServerError().body("Failed to publish event: " + ex.getMessage()));
	}
}

//Understand the API
//Item -	Value
//Method	- POST
//Endpoint - 	http://localhost:8080/api/orders/publish
//Request body	- JSON
//Action -	Publishes an event to order-events
//Response	- Kafka partition and offset