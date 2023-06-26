package com.cts.MovieAppFse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import com.cts.MovieAppFse.exception.MovieNotFoundException;
import com.cts.MovieAppFse.model.Movie;
import com.cts.MovieAppFse.model.Ticket;
import com.cts.MovieAppFse.model.User;
import com.cts.MovieAppFse.model.UserLogin;
import com.cts.MovieAppFse.repository.MovieRepository;
import com.cts.MovieAppFse.repository.TicketRepository;
import com.cts.MovieAppFse.repository.UserRepository;
import com.cts.MovieAppFse.service.MovieService;
import com.cts.MovieAppFse.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.mockito.Mockito.when;

@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest

public class MovieControllerTest {
	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private MovieRepository movieRepository;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@MockBean
	private MovieService movieService;
	
	@MockBean
	private UserService userService;
	
	
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@BeforeAll
	public void before() {
		setUp();
	}
	
	protected String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	protected <T> T mapFromJson(String json,Class<T> clazz) throws JsonParseException,JsonMappingException,IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
		
	}
	
	
	@Test
	@DisplayName("Testing register user")
	public void TestRegister() throws Exception{
		String uri = "/register";
		User user = new User(103,"srk@gmail.com","Sharukh","Khan","srk@123","1234567890");
		String body = mapToJson(user);
		when(userRepository.save(user)).thenReturn(user);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).
				content(body)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("User Registered Succesfully!!", content);
	}
	
	
	@Test
	@DisplayName("Testing login user")
	public void TestLogin() throws Exception{
		String uri = "/login";
		UserLogin user = new UserLogin("srk@gmail.com","srk@123");
		User user1=new User(123,"mohit","chaudhari","c@gmail.com","pca@123","7658901234");
		String body = mapToJson(user);
		when(userService.loginUser(Mockito.any(UserLogin.class))).thenReturn(user1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).
				content(body)).andReturn();
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
	
	
	@Test
	@DisplayName("Testing forget password")
	public void TestForgot() throws Exception{
		String uri = "/forgot";
		UserLogin user = new UserLogin("srk@gmail.com","srk@123");
		String body = mapToJson(user);
		when(userService.forgetPassword(Mockito.any(UserLogin.class))).thenReturn(true);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).
				content(body)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String msg = mvcResult.getResponse().getContentAsString();
		assertEquals("Password Updated Succesfully!!",msg);
		
		
		when(userService.forgetPassword(Mockito.any(UserLogin.class))).thenReturn(false);
		mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).
				content(body)).andReturn();
		msg = mvcResult.getResponse().getContentAsString();
		assertEquals("Something went wrong!!",msg);
	}
	
	@Test
	@DisplayName("Testing get all movies")
	public void TestGetAllMovies() throws Exception{
		String uri = "/all";
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(new Movie.MovieId("Pathan","PVR"),100));
		movies.add(new Movie(new Movie.MovieId("Pathan","INOX"),200));
		
		
		when(movieRepository.findAll()).thenReturn(movies);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String list = mvcResult.getResponse().getContentAsString();
		
		Movie[] moviesList = mapFromJson(list, Movie[].class);
		assertEquals(2, moviesList.length);
	}
	
	
	@Test
	@DisplayName("Testing search movie by name ")
	public void TestSearchMoviesByName() throws Exception{
		String uri ="/movies/search/P";
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(new Movie.MovieId("Pathan", "PVR"),10));
		
		when(movieService.SearchMovie(Mockito.anyString())).thenReturn(movies);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String list = mvcResult.getResponse().getContentAsString();
		Movie[] moviesList = mapFromJson(list, Movie[].class);
		System.out.println(list);
		assertEquals(1, moviesList.length);
		
	}
	
	@Test
	@DisplayName("Testing add movie to server")
	public void testAddMovie() throws Exception{
		String uri = "/movie/add";
		Movie movie = new Movie(new Movie.MovieId("pathan", "inox"),20);
		String body = mapToJson(movie);
		
		when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).
				content(body)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Movie resMovie = mapFromJson(content, Movie.class);
		assertEquals(movie.getMid().getMovieName(), resMovie.getMid().getMovieName());
		
	}
	
	@Test
	@DisplayName("Testing book ticket for user")
	public void testBookTicket() throws MovieNotFoundException,Exception{
		String uri = "/bookTicket";
		List<String> seatsList = new ArrayList<>();
		seatsList.add("h1");
		seatsList.add("h2");
		seatsList.add("h3");
		Ticket ticket = new Ticket(101,"Pathan","PVR",3,seatsList);
		Movie.MovieId key = new Movie.MovieId("Pathan","PVR");
		String body = mapToJson(ticket);
		
		when(movieService.updateTicketCount(Mockito.any(Ticket.class))).thenReturn(true);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).
				content(body)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String msg = mvcResult.getResponse().getContentAsString();
		assertEquals("Ticket Booked",msg);
	}
	
	@Test
	@DisplayName("Testing delete movie from server")
	public void TestDelteMovie() throws Exception{
		String uri = "/delete/Pathan/PVR";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String msg = mvcResult.getResponse().getContentAsString();
		assertEquals("Movie Deleted Succesfully!!",msg);
	}
	

}
