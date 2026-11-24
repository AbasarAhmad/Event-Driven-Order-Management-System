
package com.example.kafkalearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_id", nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal amount;

	private String status;

	private LocalDateTime createdAt;

	public Order(String orderId, BigDecimal amount, String status) {

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
}
