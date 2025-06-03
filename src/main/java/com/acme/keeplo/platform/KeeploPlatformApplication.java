package com.acme.keeplo.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * KeeploPlatformApplication
 *
 * @summary
 * The main class of the Keeplo Platform application.
 * It is responsible for starting the Spring Boot application.
 * It also enables JPA auditing.
 *
 * @since 1.0
 */

@EnableJpaAuditing
@SpringBootApplication
public class KeeploPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeeploPlatformApplication.class, args);
	}

}
