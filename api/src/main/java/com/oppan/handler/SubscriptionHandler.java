package com.oppan.handler;

import com.oppan.dto.UserDto;
import com.oppan.exception.GeneralException;
import com.oppan.exception.UploadRequestException;
import com.oppan.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class SubscriptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionHandler.class);

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionHandler(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public void subscribeUser(String userName) throws GeneralException {
        try {
            LOG.info("Subscribing user : " + userName);
            UserDto userDto = new UserDto(userName);
            subscriptionService.subscribe(userDto);
        } catch (UploadRequestException e) {
            LOG.error("Error while publishing of user subscription request : " + e.getMessage());
            throw new GeneralException(e.getMessage());
        }
    }
}
