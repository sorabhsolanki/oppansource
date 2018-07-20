package com.oppan.message.publisher.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppan.dto.UserDto;
import com.oppan.exception.UploadRequestException;
import com.oppan.message.publisher.IPublisher;
import com.oppan.prop.BasePublisherProp;
import com.oppan.util.ConfigUtil;
import com.oppan.util.OppansourcePropertyEnum;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Class responsible for publishing new user subscription i.e. userDto to MQ
 */
@Component
@DependsOn("startupService")
@Qualifier("subscribeUserPublisher")
public class SubscribeUserPublisher extends BasePublisherProp implements IPublisher<UserDto>{

    private static final Logger LOG = LoggerFactory.getLogger(SubscribeUserPublisher.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void publishMessage(UserDto userDto, String topic) throws UploadRequestException {
        Producer<String, String> producer = null;
        try {
            if (!ConfigUtil
                    .getPropertyValue(OppansourcePropertyEnum.KAFKA_CONNECTION_REQUIRED_CONFIG,
                            Boolean.class)) {
                LOG.info("No connection to kafka is required, as it is set to false.");
                return;
            }
            LOG.info(String.format("Publishing message %s on topic %s ", userDto, topic));
            producer = new KafkaProducer<>(getConfigProperties());
            RecordMetadata metadata;

            String message = objectMapper.writeValueAsString(userDto);
            metadata = producer
                    .send(new ProducerRecord<>(topic, message)).get();
            if (metadata == null) {
                LOG.error("Kafka Send Error.");
                throw new UploadRequestException("Kafka Send Error.");
            } else {
                LOG.info(String.format("Message sent successfully, on topic:: %s, partition :: %s, "
                        , metadata.topic(), metadata.partition()));
            }
        } catch (Exception e) {
            throw new UploadRequestException(e.getMessage(), e);
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }
}
