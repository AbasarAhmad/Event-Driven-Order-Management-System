package com.example.kafkalearning.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafkalearning.event.PaymentRequestEvent;
import com.example.kafkalearning.service.PaymentProcessingService;

@Service
public class PaymentConsumer {

	private final PaymentProcessingService paymentProcessingService;

	public PaymentConsumer(PaymentProcessingService paymentProcessingService) {

		this.paymentProcessingService = paymentProcessingService;
	}

	@KafkaListener(topics = "payment-events")
	public void consumePaymentEvent(PaymentRequestEvent event) {

		System.out.println("Received payment event for order: " + event.getOrderId());

		paymentProcessingService.processPayment(event);
	}
}