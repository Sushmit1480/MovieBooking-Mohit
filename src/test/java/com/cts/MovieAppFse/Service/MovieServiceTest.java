package com.cts.MovieAppFse.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.MovieAppFse.exception.MovieNotFoundException;
import com.cts.MovieAppFse.model.Movie;
import com.cts.MovieAppFse.model.Ticket;
import com.cts.MovieAppFse.repository.MovieRepository;
import com.cts.MovieAppFse.service.MovieService;

@SpringBootTest
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;
	
	@MockBean
	private MovieRepository movieRepository;
	
	@Test
	@DisplayName("Testing Movie Service class loading")
	public void testServiceClass() {
		assertThat(movieService).isNotNull();
	}
	
	@Test
	@DisplayName("Testing search movie inside movieservice")
	public void testSearchMovie(){
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(new Movie.MovieId("pathan","pvr"),100));
		movies.add(new Movie(new Movie.MovieId("pathan","inox"),200));
		
		when(movieRepository.findAll()).thenReturn(movies);
		
		assertEquals(movies, movieService.SearchMovie("pathan")); 		
		
	}
	
	
	@Test
	@DisplayName("Testing update ticket count in movie service")
	public void testupdateTicketCount() throws MovieNotFoundException{
		List<String> seatsList = new ArrayList<>();
		seatsList.add("h1");
		seatsList.add("h2");
		seatsList.add("h3");
		Ticket ticket = new Ticket(101,"pathan","pvr",3,seatsList);
		
		Movie.MovieId movieKey = new Movie.MovieId("pathan", "pvr");
		Optional<Movie> movie = Optional.of(new Movie(movieKey,5));
		
		
		when(movieRepository.findById(Mockito.any(Movie.MovieId.class))).thenReturn(movie);
		Optional<Movie> result = movieRepository.findById(movieKey);
		assertNotNull(result);
		
		
		
		assertEquals(movieService.updateTicketCount(ticket),true);
	}
	
	
}
