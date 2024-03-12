package com.example.TechTrekBackend.Filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//
//import java.io.IOException;
//
//public class URLPatternFilter implements Filter {
//
//    Logger logger = (Logger) LoggerFactory.getLogger(URLPatternFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization code (optional)
//    }
//
//    @Order(2)
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        // Check if the request URI matches the desired pattern
//        if (request instanceof HttpServletRequest) {
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            String requestURI = httpRequest.getRequestURI();
//            if ("/allProducts".equals(requestURI)) {
//                //implement redis cache here
//                System.out.println("Request for /getProducts detected");
//            }
//            logger.info("entered url filter");
//        }
//
//        // Continue with the filter chain
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup code (optional)
//    }
//}


import com.example.TechTrekBackend.config.RedisConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

@Component
public class URLPatternFilter implements Filter {

    private static final String CACHE_KEY = "allProductsCache";

    private final Logger logger = LoggerFactory.getLogger(URLPatternFilter.class);

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Check if the request URI matches the desired pattern
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestURI = httpRequest.getRequestURI();
            if ("/api/v1/products/allProducts".equals(requestURI)) {
                logger.info("Entered URL filter with /allProducts");
            }
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    // Other methods (init, destroy) can remain unchanged
}
