package com.cts.MovieAppFse.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

//import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class MovieController {
	
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private MovieRepository movieRepository;
		
		@Autowired
		private TicketRepository ticketRepository;
		
		@Autowired
		private MovieService movieService;
		
		@Autowired
		private UserService userService;
		
//		@Autowired
//	    private KafkaTemplate<String, Object> kafkaTemplate;
//
//	    @Autowired
//	    private NewTopic topic;
	
	@GetMapping("/check")
	public String CheckRoute() {
		return "Check";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> regiserUser(@RequestBody User user){
		Random rnd = new Random();
		int loginid=100000 + rnd.nextInt(900000);
		user.setLoginid(loginid);
		userRepository.save(user);
		return new ResponseEntity<String>("User Registered Succesfully!!", HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public User loginUser(@RequestBody UserLogin userlogin) {
		return userService.loginUser(userlogin);
	}
	
	
	
	@PostMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody UserLogin userlogin){
		if(userService.forgetPassword(userlogin)){
			return new ResponseEntity<String>("Password Updated Succesfully!!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something went wrong!!", HttpStatus.OK);
	}
	
	
	
	@PostMapping("/movie/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
		movie.setMid(new Movie.MovieId(movie.getMid().getMovieName().toLowerCase(), movie.getMid().getTheatreName().toLowerCase()));
	//	kafkaTemplate.send(topic.name(),"Movie added succesfully by Admin");
		movieRepository.save(movie);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	
	@PostMapping("/bookTicket")
	public ResponseEntity<String> bookTicket(@RequestBody Ticket ticket) throws MovieNotFoundException{
		if(movieService.updateTicketCount(ticket)) {
			Random rnd = new Random();
			int ticketId=100000 + rnd.nextInt(900000);
			ticket.setTicketId(ticketId);
			ticketRepository.save(ticket);
	//		kafkaTemplate.send(topic.name(),"tickets booked by user");
			return new ResponseEntity<String>("Ticket Booked", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("tickets not available! ", HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> allMovies(){
		List<Movie> movies=movieRepository.findAll();
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/movies/search/{movieName}")
	public ResponseEntity<?> SearchMovie(@PathVariable("movieName") String movieName){
		List<Movie> searchedMovies=movieService.SearchMovie(movieName);
		
		if(searchedMovies.size()!=0) {
			return new ResponseEntity<List<Movie>>(searchedMovies, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Movies Found", HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{movieName}/{theatreName}")
	public ResponseEntity<?> deleteMovie(@PathVariable("movieName") String movieName,@PathVariable("theatreName") String theatreName){

		
		movieRepository.deleteById(new Movie.MovieId(movieName, theatreName));
//		kafkaTemplate.send(topic.name(),"Movie Deleted by the Admin. "+movieName+" is now not available");
		return new ResponseEntity<String>("Movie Deleted Succesfully!!", HttpStatus.OK);
		
	}
	
}
