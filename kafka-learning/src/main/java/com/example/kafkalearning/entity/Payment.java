
package com.example.kafkalearning.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_id", nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	private LocalDateTime createdAt;

	public Payment() {
	}

	public Payment(String orderId, BigDecimal amount, PaymentStatus status) {

		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
		this.createdAt = LocalDateTime.now();
	}

	@PrePersist
	public void prePersist() {

		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}

	public Long getId() {
		return id;
	}

	public String getOrderId() {
		return orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
}
