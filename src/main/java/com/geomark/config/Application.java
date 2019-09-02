package com.geomark.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

/**
 *  Spring Boot application initializer
 *
 *  @author Georgios Markakis
 */
@SpringBootApplication
@EnableCaching
@Configuration
@ComponentScan(basePackages = { "com.geomark" })
@EnableJpaRepositories("com.geomark.model")
@EnableAsync
@EnableAutoConfiguration
public class Application {

	/**
	 * LOGGER
	 */
	Logger logger = LoggerFactory.getLogger(Application.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Logs available Beans registered in Context when debug is on
	 * 
	 * @param ctx
	 * @return
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.debug("Listing Spring Boot Beans:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				logger.debug(beanName);
			}

		};
	}

}