
package com.example.kafkalearning.service;

import com.example.kafkalearning.entity.Order;
import com.example.kafkalearning.repository.OrderRepository;
import com.example.kafkalearning.repository.ProcessedEventRepository;
import com.example.kafkalearning.event.OrderEvent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderProcessingService {

	private final OrderRepository orderRepository;
	private final ProcessedEventRepository processedEventRepository;

	public OrderProcessingService(OrderRepository orderRepository, ProcessedEventRepository processedEventRepository) {

		this.orderRepository = orderRepository;
		this.processedEventRepository = processedEventRepository;
	}

	@Transactional
	public boolean processOrderEvent(OrderEvent event) {

		int inserted = processedEventRepository.insertIfNotExists(event.getEventId(), LocalDateTime.now());

		if (inserted == 0) {
			System.out.println("Duplicate event ignored: " + event.getEventId());
			return false;
		}

		if (event.getAmount() < 0) {

			System.err.println("Invalid amount received for order " + event.getOrderId());

			throw new IllegalArgumentException("Invalid order amount!");
		}

		Order order = new Order(event.getOrderId(), BigDecimal.valueOf(event.getAmount()), "CREATED");

		orderRepository.save(order);

		System.out.println("Order saved successfully: " + event.getOrderId());

		return true;
	}
}
