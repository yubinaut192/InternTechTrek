package com.example.TechTrekBackend.Filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


import java.io.IOException;

public class LoggingFilter implements Filter {
    Logger logger = (Logger) LoggerFactory.getLogger(LoggingFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code (optional)
    }


    @Order(1)
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        logger.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        chain.doFilter(request, response);
        logger.info(
                "Logging Response :{}",
                res.getContentType());
    }


    @Override
    public void destroy() {
        // Cleanup code (optional)
    }
}
