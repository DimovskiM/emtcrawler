package com.mdkg.emtcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmtcrawlerApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmtcrawlerApplication.class, args);


	}

}

