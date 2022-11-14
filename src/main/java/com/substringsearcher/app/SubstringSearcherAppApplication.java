package com.substringsearcher.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SubstringSearcherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubstringSearcherAppApplication.class, args);
	}

}
