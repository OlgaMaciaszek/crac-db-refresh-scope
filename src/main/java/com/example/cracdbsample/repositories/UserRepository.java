package com.example.cracdbsample.repositories;

import com.example.cracdbsample.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Olga Maciaszek-Sharma
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> { }