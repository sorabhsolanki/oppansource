package com.oppan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 */
@SpringBootApplication(scanBasePackages = "com.oppan.*")
public class Oppansource {
    public static void main(String[] args) {
        SpringApplication.run(Oppansource.class, args);
    }
}
