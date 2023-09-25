package com.example.cracdbsample;

import com.example.cracdbsample.entities.User;
import com.example.cracdbsample.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CracDbSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CracDbSampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) {
		return (String[] args) -> {
			User user1 = new User("John", "john@domain.com");
			User user2 = new User("Julie", "julie@domain.com");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
