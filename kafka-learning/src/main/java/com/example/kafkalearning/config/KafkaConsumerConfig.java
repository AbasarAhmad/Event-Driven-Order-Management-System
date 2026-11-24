
package com.example.kafkalearning.config;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> kafkaTemplate) {

		DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
				(record, exception) -> new TopicPartition(record.topic() + ".DLT", record.partition()));

		FixedBackOff backOff = new FixedBackOff(2000L, 2L);

		return new DefaultErrorHandler(recoverer, backOff);
	}
	
//	DeadLetterPublishingRecoverer	Publishes failed messages to the DLT
//	record.topic() + ".DLT"	Creates the destination topic name
//	record.partition()	Sends the failed message to the corresponding DLT partition
//	FixedBackOff(2000L, 2L)	Waits 2 seconds between retries, with 2 retries
}
