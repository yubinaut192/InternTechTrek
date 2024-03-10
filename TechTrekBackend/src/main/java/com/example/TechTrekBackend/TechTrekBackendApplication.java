package com.example.TechTrekBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 36000)
@SpringBootApplication
public class TechTrekBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TechTrekBackendApplication.class, args);
	}

}
