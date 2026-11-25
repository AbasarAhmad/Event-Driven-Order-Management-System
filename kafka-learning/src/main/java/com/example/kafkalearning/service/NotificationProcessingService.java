package com.example.kafkalearning.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kafkalearning.entity.Notification;
import com.example.kafkalearning.event.PaymentCompletedEvent;
import com.example.kafkalearning.repository.NotificationRepository;

@Service
public class NotificationProcessingService {

	private final NotificationRepository notificationRepository;

	public NotificationProcessingService(NotificationRepository notificationRepository) {

		this.notificationRepository = notificationRepository;
	}

	@Transactional
	public void processNotification(PaymentCompletedEvent event) {

		Notification notification = new Notification(event.getOrderId(),
				"Payment completed successfully for order " + event.getOrderId());

		notificationRepository.save(notification);

		System.out.println("Notification generated for order: " + event.getOrderId());
	}
}