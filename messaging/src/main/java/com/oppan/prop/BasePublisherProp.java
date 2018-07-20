package com.oppan.prop;

import com.oppan.exception.GeneralException;
import com.oppan.util.ConfigUtil;
import com.oppan.util.KafkaPublisherConstants;
import com.oppan.util.OppansourcePropertyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 */
public class BasePublisherProp {

    private static final Logger LOG = LoggerFactory.getLogger(BasePublisherProp.class);

    private final Properties configProperties = new Properties();

    public BasePublisherProp() {
        try {
            initPublisherProperties();
        } catch (GeneralException e) {
            LOG.error("Error during initialization of kafka publisher." + e.getMessage());
        }
    }

    private void initPublisherProperties() throws GeneralException {
        configProperties.put(KafkaPublisherConstants.KAFKA_SERVERS_CONFIG,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.BOOTSTRAP_SERVERS_CONFIG, String.class));
        configProperties.put(KafkaPublisherConstants.KAFKA_ACKS,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.KAFKA_ACKS, String.class));
        configProperties.put(KafkaPublisherConstants.KAFKA_RETRIES,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.KAFKA_RETRIES, Integer.class));
        configProperties.put(KafkaPublisherConstants.KAFKA_BATCH_SIZE,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.KAFKA_BATCH_SIZE, Integer.class));
        configProperties.put(KafkaPublisherConstants.KAFKA_LINGER_MS,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.KAFKA_LINGER_MS, Integer.class));
        configProperties.put(KafkaPublisherConstants.KAFKA_BUFFER_MEMORY,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.KAFKA_BUFFER_MEMORY, Integer.class));
        configProperties.put(KafkaPublisherConstants.KAFKA_BLOCK_TIME_MS,
                ConfigUtil.getPropertyValue(OppansourcePropertyEnum.KAFKA_BLOCK_TIME_MS, Integer.class));

        configProperties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        configProperties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
    }

    public Properties getConfigProperties() {
        return configProperties;
    }

}
