
package com.example.kafkalearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kafkalearning.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(String orderId);

}
