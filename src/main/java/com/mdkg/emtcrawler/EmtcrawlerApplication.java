package com.mdkg.emtcrawler;

import com.mdkg.emtcrawler.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmtcrawlerApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmtcrawlerApplication.class, args);

	}

}

