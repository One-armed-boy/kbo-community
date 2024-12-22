package com.frog.kbo_community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KboCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(KboCommunityApplication.class, args);
	}

}
