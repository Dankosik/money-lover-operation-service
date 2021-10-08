package com.example.moneyloveroperationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MoneyLoverOperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyLoverOperationServiceApplication.class, args);
    }

}
