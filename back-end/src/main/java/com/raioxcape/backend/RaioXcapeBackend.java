package com.raioxcape.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class RaioXcapeBackend {

    public static void main(String[] args) {
        SpringApplication.run(RaioXcapeBackend.class, args);
    }
}
