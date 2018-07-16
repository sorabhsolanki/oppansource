package com.oppan.controller;

import com.oppan.handler.SubscriptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
public class OppanController {

  private static final Logger LOG = LoggerFactory.getLogger(OppanController.class);

  private final SubscriptionHandler subscriptionHandler;

  public OppanController(SubscriptionHandler subscriptionHandler) {
    this.subscriptionHandler = subscriptionHandler;
  }

  /*
  API for health check.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity healthCheck() {

    subscriptionHandler.subscribeUser("sorabh");

    LOG.info("Request received for healthCheck.");
    return ResponseEntity.status(HttpStatus.OK)
        .body("Oppansource service is up and running!");
  }
}
