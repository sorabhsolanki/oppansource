package com.oppan.util;

/**
 */
public interface KafkaPublisherConstants {

  String KAFKA_SERVERS_CONFIG = "bootstrap.servers";
  String KAFKA_ACKS = "acks";
  String KAFKA_RETRIES = "retries";
  String KAFKA_BATCH_SIZE = "batch.size";
  String KAFKA_LINGER_MS = "linger.ms";
  String KAFKA_BUFFER_MEMORY = "buffer.memory";
  String KAFKA_BLOCK_TIME_MS = "max.block.ms";
}
