package com.cts.MovieAppFse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.MovieAppFse.exception.MovieNotFoundException;
import com.cts.MovieAppFse.model.Movie;
import com.cts.MovieAppFse.model.Ticket;
import com.cts.MovieAppFse.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	public List<Movie> SearchMovie(String movieName) {
		String movName=movieName.toLowerCase();
		List<Movie> movies=movieRepository.findAll();
		
		List<Movie> searchedMovie=movies.stream().filter((movie)->movie.getMid().getMovieName()
				.contains(movName)).collect(Collectors.toList());
		return searchedMovie;	
		
		
	}
	
	public boolean updateTicketCount(Ticket ticket) throws MovieNotFoundException {
		Optional<Movie> movie=movieRepository.findById(new Movie.MovieId(ticket.getMovieName(), ticket.getTheatreName()));
		
		if(!movie.isPresent()){
			throw new MovieNotFoundException("Movie Not Found");
		}

			if(ticket.getNumberOfTickets()< movie.get().getAllotedSeats()) {
				int count=movie.get().getAllotedSeats()-ticket.getNumberOfTickets();
				movie.get().setAllotedSeats(count);
				movieRepository.save(movie.get());
				return true;
			}
			return false;
		
		
	}
}
