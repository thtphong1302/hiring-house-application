package com.example.hiringhouseapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HiringHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiringHouseApplication.class, args);
    }

}
