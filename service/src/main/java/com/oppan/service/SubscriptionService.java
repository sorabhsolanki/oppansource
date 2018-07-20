package com.oppan.service;

import com.oppan.dto.UserDto;
import com.oppan.exception.GeneralException;
import com.oppan.exception.UploadRequestException;
import com.oppan.message.publisher.IPublisher;
import com.oppan.util.ConfigUtil;
import com.oppan.util.OppansourcePropertyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class SubscriptionService {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionService.class);

    private final IPublisher<UserDto> userSubscriptionPublisher;
    private final String TOPIC_NAME;

    @Autowired
    public SubscriptionService(@Qualifier("subscribeUserPublisher") IPublisher<UserDto> userSubscriptionPublisher) {
        this.userSubscriptionPublisher = userSubscriptionPublisher;
        try {
            TOPIC_NAME = ConfigUtil
                    .getPropertyValue(OppansourcePropertyEnum.KAFKA_SUBSCRIBE_USER_TOPIC, String.class);
        } catch (GeneralException e) {
            throw new RuntimeException("Error occured while fetching KAFKA_SUBSCRIBE_USER_TOPIC detail.");
        }
    }


    public void subscribe(UserDto userDto) throws UploadRequestException {
        LOG.info(String.format("Publishing new user %s subscription info on MQ : ", userDto));
        userSubscriptionPublisher.publishMessage(userDto, TOPIC_NAME);
    }
}
