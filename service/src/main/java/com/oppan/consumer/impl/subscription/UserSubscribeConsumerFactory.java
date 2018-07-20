package com.oppan.consumer.impl.subscription;

import com.oppan.consumer.IKafkaConsumerFactory;
import com.oppan.exception.GeneralException;
import com.oppan.user.UserRepository;
import com.oppan.util.ConfigUtil;
import com.oppan.util.OppansourcePropertyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Factory class for initializing Consumer on user-subscribe Topic
 */
@Service
@DependsOn("startupService")
public class UserSubscribeConsumerFactory implements IKafkaConsumerFactory{

    private static final Logger LOG = LoggerFactory.getLogger(UserSubscribeConsumerFactory.class);

    private final ExecutorService executorService;
    private final int consumerSize;
    private final UserRepository userRepository;

    public UserSubscribeConsumerFactory(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
        try {
            this.userRepository = userRepository;
            consumerSize = ConfigUtil
                    .getPropertyValue(OppansourcePropertyEnum.KAFKA_USER_SUBSCRIBE_CONSUMER_SIZE, Integer.class);
            this.executorService = Executors.newFixedThreadPool(consumerSize);
            registerQueueFactory();
        } catch (GeneralException ex){
            throw new RuntimeException("Exception occurred while initializing user-subscribe consumers");
        }
    }

    @Override
    public void registerQueueFactory() throws GeneralException {
        if (!ConfigUtil
                .getPropertyValue(OppansourcePropertyEnum.KAFKA_CONNECTION_REQUIRED_CONFIG,
                        Boolean.class)) {
            LOG.info("No connection to kafka is required, as it is set to false.");
            return;
        }
        LOG.info(String.format("Starting %s consumers and registering them to \"user-subscribe\" topic",
                consumerSize));
        final String topic = ConfigUtil
                .getPropertyValue(OppansourcePropertyEnum.KAFKA_SUBSCRIBE_USER_TOPIC, String.class);
        for (int i = 0; i < consumerSize; i++) {
            executorService.submit(new UserSubscriptionConsumer(getProperty(), topic, userRepository));
        }
    }

    @Override
    public void deRegisterQueueFactory() {

    }
}
