package com.mdkg.emtcrawler;

import com.mdkg.emtcrawler.parser.MockParser;
import com.mdkg.emtcrawler.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmtcrawlerApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(EmtcrawlerApplication.class, args);
		context.getBean(MockParser.class).buildDatabase();

	}

}

