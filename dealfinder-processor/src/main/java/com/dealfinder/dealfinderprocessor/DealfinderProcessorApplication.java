package com.dealfinder.dealfinderprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.dealfinder")
@EnableJpaRepositories(basePackages = "com.dealfinder")
@EntityScan(basePackages = "com.dealfinder")
public class DealfinderProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealfinderProcessorApplication.class, args);
    }

}
