package com.cts.MovieAppFse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cts.MovieAppFse.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {
	public User findByEmail(String email);
}
