package com.raioxcape.backend.config;

import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Value(value = "${cors.mapping}")
    private String mapping;

    @Value(value = "${cors.allowed-origins}")
    private List<String> allowedOrigins;

    @Value(value = "${cors.allowed-methods}")
    private List<String> allowedMethods;

    @Value(value = "${cors.allowed-headers}")
    private List<String> allowedHeaders;

    @Value(value = "${cors.exposed-headers}")
    private List<String> exposedHeaders;

    @Value(value = "${cors.allow-credentials}")
    private boolean allowCredentials;

    @Value(value = "${cors.access-control-max-age}")
    private int accessControlMaxAge;

    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry
            .addMapping(this.mapping)
            .allowedOrigins(this.allowedOrigins.toArray(new String[0]))
            .allowedMethods(this.allowedMethods.toArray(new String[0]))
            .allowedHeaders(this.allowedHeaders.toArray(new String[0]))
            .exposedHeaders(this.exposedHeaders.toArray(new String[0]))
            .allowCredentials(this.allowCredentials)
            .maxAge(this.accessControlMaxAge);
    }
}
