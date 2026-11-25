package com.example.kafkalearning.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafkalearning.event.PaymentCompletedEvent;

@Service
public class PaymentCompletionProducer {

	private static final String TOPIC = "payment-completed-events";

	private final KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

	public PaymentCompletionProducer(KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate) {

		this.kafkaTemplate = kafkaTemplate;
	}

	public CompletableFuture<SendResult<String, PaymentCompletedEvent>> publishPaymentCompletedEvent(
			PaymentCompletedEvent event) {

		return kafkaTemplate.send(TOPIC, event.getOrderId(), event);
	}
}