package com.oppan.consumer;

import com.oppan.exception.GeneralException;
import com.oppan.util.ConfigUtil;
import com.oppan.util.KafkaConsumerConstants;
import com.oppan.util.OppansourcePropertyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Consumer factory interface for initializing consumers
 */
public interface IKafkaConsumerFactory {

    default Properties getProperty() {
        try {
            Properties configProperties = new Properties();
            configProperties.put(KafkaConsumerConstants.KAFKA_SERVERS_CONFIG,
                    ConfigUtil.getPropertyValue(OppansourcePropertyEnum.BOOTSTRAP_SERVERS_CONFIG, String.class));
            configProperties.put(KafkaConsumerConstants.KAFKA_USER_SUBSCRIBE_GROUP_ID,
                    ConfigUtil.getPropertyValue(OppansourcePropertyEnum.GROUP_ID_USER_SUBSCRIBE_CONFIG, String.class));
            configProperties.put(KafkaConsumerConstants.KAFKA_ENABLE_AUTO_COMMIT,
                    ConfigUtil.getPropertyValue(OppansourcePropertyEnum.ENABLE_AUTO_COMMIT_CONFIG, Boolean.class));
            configProperties.put(KafkaConsumerConstants.KAFKA_AUTO_RESET_CONFIG,
                    ConfigUtil.getPropertyValue(OppansourcePropertyEnum.AUTO_OFFSET_RESET_CONFIG, String.class));

            configProperties.put("key.deserializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
            configProperties.put("value.deserializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
            return configProperties;
        }catch (GeneralException ex){
            throw new RuntimeException("");
        }

    }

    void registerQueueFactory() throws GeneralException;

    void deRegisterQueueFactory();
}
