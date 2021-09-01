package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/helloOdin")
	public String helloAll() {
		return "Hi, Bobi, Emi, Lysi and Radi";
	}
}
