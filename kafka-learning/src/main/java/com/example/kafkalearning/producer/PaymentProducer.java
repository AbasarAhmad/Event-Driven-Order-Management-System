package com.example.kafkalearning.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafkalearning.event.PaymentRequestEvent;

@Service
public class PaymentProducer {

	private static final String TOPIC = "payment-events";

	private final KafkaTemplate<String, PaymentRequestEvent> kafkaTemplate;

	public PaymentProducer(KafkaTemplate<String, PaymentRequestEvent> kafkaTemplate) {

		this.kafkaTemplate = kafkaTemplate;
	}

	public CompletableFuture<SendResult<String, PaymentRequestEvent>> sendPaymentRequest(PaymentRequestEvent event) {

		return kafkaTemplate.send(TOPIC, event.getOrderId(), event);
	}
}