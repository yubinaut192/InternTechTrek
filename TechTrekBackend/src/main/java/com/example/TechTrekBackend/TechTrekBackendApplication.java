package com.example.TechTrekBackend;

import com.example.TechTrekBackend.Filter.LoggingFilter;
import com.example.TechTrekBackend.Filter.URLPatternFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 36000)
@SpringBootApplication
public class TechTrekBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TechTrekBackendApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<LoggingFilter> loggingFilter() {
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new LoggingFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<URLPatternFilter> urlPatternFilter() {
		FilterRegistrationBean<URLPatternFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new URLPatternFilter());
		registrationBean.addUrlPatterns("/api/v1/products/allProducts");
		registrationBean.setOrder(2);
		return registrationBean;
	}
}
