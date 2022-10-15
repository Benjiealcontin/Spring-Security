package com.operation.SecuritySpring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@ComponentScan("Controller")
@ComponentScan("UserdetailsService")
@ComponentScan("Service")
@ComponentScan("Config")
@EntityScan("Model")
@EnableJpaRepositories("Repository")
public class SecuritySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySpringBootApplication.class, args);
	}

}
