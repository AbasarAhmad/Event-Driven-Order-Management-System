
package com.example.kafkalearning.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kafkalearning.entity.ProcessedEvent;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, Long> {

	@Modifying
	@Query(value = """
			INSERT IGNORE INTO processed_events
			(event_id, processed_at)
			VALUES (:eventId, :processedAt)
			""", nativeQuery = true)
	int insertIfNotExists(@Param("eventId") String eventId, @Param("processedAt") LocalDateTime processedAt);
}
