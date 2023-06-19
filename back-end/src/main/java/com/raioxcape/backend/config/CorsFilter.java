package com.raioxcape.backend.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Value(value = "${cors.allowed-origins}")
    private List<String> allowedOrigins;

    private final List<String> allowedMethods = Arrays.asList(
        HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
        HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name()
    );

    private final List<String> allowedHeaders = Arrays.asList(
        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT,
        HttpHeaders.ORIGIN, HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
        HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION, "X-Requested-With"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", String.join(", ", this.allowedOrigins));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", String.join(", ", this.allowedMethods));
        httpServletResponse.setHeader("Access-Control-Allow-Headers", String.join(", ", this.allowedHeaders));
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");

        if (HttpMethod.OPTIONS.name().equalsIgnoreCase((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
