package com.example.kafkalearning.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderId;

	private String message;

	private LocalDateTime sentAt;

	public Notification() {
	}

	public Notification(String orderId, String message) {

		this.orderId = orderId;
		this.message = message;
		this.sentAt = LocalDateTime.now();
	}

	@PrePersist
	public void prePersist() {

		if (sentAt == null) {
			sentAt = LocalDateTime.now();
		}
	}

	public Long getId() {
		return id;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}
}