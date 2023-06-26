package com.cts.MovieAppFse.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieDeatailsTest {
	
	@Test
	@DisplayName("Testing movie database")
	public void testMobie() {
		Movie movie = new Movie();
		assertThat(movie).isNotNull();
	}
	
	@Test
	@DisplayName("Testing getter setter of Movie model")
	public void GetSetMovieTest() {
		Movie movie=new Movie();
		Movie.MovieId movieId=new Movie.MovieId();
		movieId.setMovieName("RRR");
		movieId.setTheatreName("Inox");
		
		movie.setMid(movieId);

		movie.setAllotedSeats(300);
		assertEquals("RRR",movie.getMid().getMovieName());
		assertEquals("Inox", movie.getMid().getTheatreName());
		assertEquals(300, movie.getAllotedSeats());
		
		
	}
	
	@Test
	@DisplayName("Testing constructor of Movie model")
	public void checkConstructor() {
		Movie movie=new Movie(new Movie.MovieId("RRR", "Inox"),300);
		assertEquals("RRR",movie.getMid().getMovieName());
		assertEquals("Inox", movie.getMid().getTheatreName());
		assertEquals(300, movie.getAllotedSeats());
	}
}
