package com.dot.onboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class OnboardApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(OnboardApplication.class, args);
    }
}
