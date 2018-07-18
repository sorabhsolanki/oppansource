package com.oppan.message.publisher.impl;

import com.oppan.dto.UserDto;
import com.oppan.exception.UploadRequestException;
import com.oppan.message.publisher.IPublisher;
import com.oppan.prop.BasePublisherProp;
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

    @Override
    public void publishMessage(UserDto userDto, String topic) throws UploadRequestException {
    }
}
