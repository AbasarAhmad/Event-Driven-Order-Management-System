package com.example.kafkalearning.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafkalearning.event.PaymentCompletedEvent;
import com.example.kafkalearning.service.NotificationProcessingService;

@Service
public class NotificationConsumer {

	private final NotificationProcessingService notificationProcessingService;

	public NotificationConsumer(NotificationProcessingService notificationProcessingService) {

		this.notificationProcessingService = notificationProcessingService;
	}

	@KafkaListener(topics = "payment-completed-events")
	public void consume(PaymentCompletedEvent event) {

		System.out.println("Received payment completion event for order: " + event.getOrderId());

		notificationProcessingService.processNotification(event);
	}
}