package com.example.cracdbrefreshscope.repositories;

import com.example.cracdbrefreshscope.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> { }