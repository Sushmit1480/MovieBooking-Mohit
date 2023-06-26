package com.cts.MovieAppFse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cts.MovieAppFse.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, Movie.MovieId> {

}
