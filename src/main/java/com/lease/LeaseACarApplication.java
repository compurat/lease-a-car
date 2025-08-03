package com.lease;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.lease.car.repository")
@SpringBootApplication
@EntityScan({"com.lease.car.model", "com.lease.car.repository"})
public class LeaseACarApplication {

    private static final Logger logger = LoggerFactory.getLogger(LeaseACarApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LeaseACarApplication.class, args);
    }

}
