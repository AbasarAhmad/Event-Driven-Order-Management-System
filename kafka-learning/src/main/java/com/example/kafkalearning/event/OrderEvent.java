
package com.example.kafkalearning.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

	private String eventId;
	private String orderId;
	private String eventType;
	private double amount;
}
