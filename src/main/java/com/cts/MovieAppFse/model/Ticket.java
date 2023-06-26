package com.cts.MovieAppFse.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TicketDetails")
public class Ticket {
	@Id
	private int ticketId;
	
	private String movieName;
	private String theatreName;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ticket(int ticketId, String movieName, String theatreName, int numberOfTickets, List<String> seats) {
		super();
		this.ticketId = ticketId;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.numberOfTickets = numberOfTickets;
		this.seats = seats;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	public List<String> getSeats() {
		return seats;
	}
	public void setSeats(List<String> seats) {
		this.seats = seats;
	}
	private int numberOfTickets;
	private List<String> seats;
	
	
}
