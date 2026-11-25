package com.example.kafkalearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kafkalearning.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

}