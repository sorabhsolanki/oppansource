package com.oppan.consumer.impl.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppan.dto.UserDto;
import com.oppan.exception.GeneralException;
import com.oppan.user.UserRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

/**
 *
 */
public class UserSubscriptionConsumer  implements Runnable{

    private static final Logger LOG = LoggerFactory.getLogger(UserSubscriptionConsumer.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final KafkaConsumer<String, String> kafkaConsumer;

    private final UserRepository userRepository;

    public UserSubscriptionConsumer(Properties configProperties, String topic
                                    , UserRepository userRepository) throws GeneralException {
        this.userRepository = userRepository;
        LOG.info("Subscribing to : " + topic);
        kafkaConsumer = new KafkaConsumer<>(configProperties);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
    }

    @Override
    public void run() {
        UserDto userDto;
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            if (records == null || records.count() == 0) {
                continue;
            }
            for (ConsumerRecord<String, String> record : records) {
                try {
                    userDto = OBJECT_MAPPER.readValue(record.value(), UserDto.class);
                    LOG.info("Reading record : " + userDto);
                    userRepository.insert(userDto);

                    kafkaConsumer.commitSync();
                } catch (IOException e) {
                    LOG.error(String.format("Error in un-marshalling. message : %s", e.getMessage()));
                }
            }
        }
    }
}
