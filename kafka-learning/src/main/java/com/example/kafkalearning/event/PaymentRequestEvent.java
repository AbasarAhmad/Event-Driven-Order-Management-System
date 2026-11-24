package com.example.kafkalearning.event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestEvent {

    private String eventId;

    private String orderId;

    private BigDecimal amount;

}