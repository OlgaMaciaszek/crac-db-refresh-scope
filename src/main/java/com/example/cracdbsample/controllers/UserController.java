package com.example.cracdbsample.controllers;

import java.util.List;

import com.example.cracdbsample.entities.User;
import com.example.cracdbsample.repositories.UserRepository;
import org.apache.commons.collections4.IterableUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	List<User> getUsers() {
		return IterableUtils.toList(userRepository.findAll());
	}

}
