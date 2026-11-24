
package com.example.kafkalearning.consumer;

import com.example.kafkalearning.event.OrderEvent;
import com.example.kafkalearning.service.OrderProcessingService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

	private final OrderProcessingService orderProcessingService;

	public OrderConsumer(OrderProcessingService orderProcessingService) {
		this.orderProcessingService = orderProcessingService;
	}

	// First consumer
//	@KafkaListener(topics = "order-events", groupId = "order-service-group")
//	public void consumeOrderEvent(OrderEvent orderEvent) {
//
//		System.out.println("========== ORDER EVENT RECEIVED ==========");
//		System.out.println("Event ID   : " + orderEvent.getEventId());
//		System.out.println("Order ID   : " + orderEvent.getOrderId());
//		System.out.println("Event Type : " + orderEvent.getEventType());
//		System.out.println("Amount     : " + orderEvent.getAmount());
//		System.out.println("==========================================");
//	}
	@KafkaListener(topics = "order-events")
	public void consume(OrderEvent event, Acknowledgment acknowledgment) {

		boolean processed = orderProcessingService.processOrderEvent(event);

		acknowledgment.acknowledge();
	}
//	Second Consumer
//	@KafkaListener(topics = "order-events", groupId = "order-service-group")
//	public void consumeOrderEventSecond(OrderEvent orderEvent) {
//
//		System.out.println("========== SECOND CONSUMER ==========");
//		System.out.println("Order ID: " + orderEvent.getOrderId());
//		System.out.println("Amount  : " + orderEvent.getAmount());
//		System.out.println("=====================================");
//	}

}

//Property	Meaning
//topics -	Topic from which messages are consumed
//groupId -	Consumer group to which this consumer belongs
//consumeOrderEvent() - Whenever Kafka delivers a message, Spring Kafka converts its JSON value into an OrderEvent object and calls this method