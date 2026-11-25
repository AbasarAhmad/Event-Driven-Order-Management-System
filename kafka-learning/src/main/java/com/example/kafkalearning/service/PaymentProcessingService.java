package com.example.kafkalearning.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kafkalearning.entity.Payment;
import com.example.kafkalearning.entity.PaymentStatus;
import com.example.kafkalearning.event.PaymentCompletedEvent;
import com.example.kafkalearning.event.PaymentRequestEvent;
import com.example.kafkalearning.producer.PaymentCompletionProducer;
import com.example.kafkalearning.repository.PaymentRepository;

@Service
public class PaymentProcessingService {

	private final PaymentRepository paymentRepository;
	private final PaymentCompletionProducer paymentCompletionProducer;

	public PaymentProcessingService(PaymentRepository paymentRepository,
			PaymentCompletionProducer paymentCompletionProducer) {

		this.paymentRepository = paymentRepository;
		this.paymentCompletionProducer = paymentCompletionProducer;
	}

	@Transactional
	public void processPayment(PaymentRequestEvent event) {

		Payment payment = new Payment(event.getOrderId(), BigDecimal.valueOf(event.getAmount().doubleValue()),
				PaymentStatus.SUCCESS);

		paymentRepository.save(payment);
		System.out.println("Payment processed successfully for order: " + event.getOrderId());

		PaymentCompletedEvent completedEvent = new PaymentCompletedEvent(event.getOrderId(),
				PaymentStatus.SUCCESS.name());

		paymentCompletionProducer.publishPaymentCompletedEvent(completedEvent);

		System.out.println("Payment completion event published for order: " + event.getOrderId());
	}
}
