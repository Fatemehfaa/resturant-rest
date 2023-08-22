package com.neshan.resturantrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResturantRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResturantRestApplication.class, args);
    }

}
