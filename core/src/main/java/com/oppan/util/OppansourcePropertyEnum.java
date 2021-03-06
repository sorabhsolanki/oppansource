package com.oppan.util;

/**
 * Class for holding properties which is used inside ConfigUtil class.
 */
public enum OppansourcePropertyEnum {

    BOOTSTRAP_SERVERS_CONFIG("kafka.bootstrap.servers", "", String.class),
    KAFKA_ACKS("kafka.acks", "all", String.class),
    KAFKA_RETRIES("kafka.retries", "", Integer.class),
    KAFKA_BATCH_SIZE("kafka.batch.size", "", Integer.class),
    KAFKA_LINGER_MS("kafka.linger.ms", "", Integer.class),
    KAFKA_BUFFER_MEMORY("kafka.buffer.memory", "", Integer.class),
    KAFKA_BLOCK_TIME_MS("kafka.block.time.ms", "", Integer.class),

    KAFKA_SUBSCRIBE_USER_TOPIC("kafka.subscribe.user.topic", "user-subscribe", String.class),
    KAFKA_CONNECTION_REQUIRED_CONFIG("kafka.server.connection.required", "true", Boolean.class),


    GROUP_ID_USER_SUBSCRIBE_CONFIG("kafka.user.subscribe.group.id", "user-subscribe-group", String.class),
    ENABLE_AUTO_COMMIT_CONFIG("kafka.enable.auto.commit", "false", Boolean.class),
    AUTO_OFFSET_RESET_CONFIG("kafka.auto.reset.config", "earliest", String.class),

    KAFKA_USER_SUBSCRIBE_CONSUMER_SIZE("kafka.user.subscribe.consumer.size", "2", Integer.class);




    private String propertyName;
    private String value;
    private Class type;

    OppansourcePropertyEnum(String propertyName, String value, Class type) {
        this.propertyName = propertyName;
        this.value = value;
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getValue() {
        return value;
    }

    public Class getType() {
        return type;
    }
}
