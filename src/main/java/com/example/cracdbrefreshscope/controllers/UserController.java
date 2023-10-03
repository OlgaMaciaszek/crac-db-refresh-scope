package com.example.cracdbrefreshscope.controllers;

import java.util.List;

import com.example.cracdbrefreshscope.entities.User;
import com.example.cracdbrefreshscope.repositories.UserRepository;
import org.apache.commons.collections4.IterableUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
