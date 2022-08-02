package com.example.cit08server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Cit08serverApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cit08serverApplication.class, args);
    }

}
