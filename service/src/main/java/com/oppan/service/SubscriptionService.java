package com.oppan.service;

import com.oppan.dto.UserDto;
import com.oppan.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class SubscriptionService {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionService.class);

    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void subscribe(UserDto userDto){
        LOG.info("Inserting subscription for user : " + userDto);
        userRepository.insert(userDto);
    }
}
