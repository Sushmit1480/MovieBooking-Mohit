package com.cts.MovieAppFse.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TicketTest {
	
	@Test
	@DisplayName("Testing ticket model")
	public void testTicket() {
		Ticket ticket = new Ticket();
		assertThat(ticket).isNotNull();
	}
	
	@Test
	@DisplayName("Testing getter setter of Ticket model")
	public void testGetSet() {
		List<String> seatsList = new ArrayList<>();
		seatsList.add("h1");
		seatsList.add("h2");
		seatsList.add("h3");
		
		Ticket ticket=new Ticket();
		
		ticket.setMovieName("RRR");
		ticket.setNumberOfTickets(5);
		ticket.setSeats(seatsList);
		ticket.setTheatreName("Inox");
		ticket.setTicketId(123);
		
		
		assertEquals("RRR", ticket.getMovieName());
		assertEquals(5, ticket.getNumberOfTickets());
		assertEquals(seatsList, ticket.getSeats());
		assertEquals("Inox",ticket.getTheatreName());
		assertEquals(123, ticket.getTicketId());
		
		
	}
	@Test
	@DisplayName("Testing constructor of Ticket model")
	public void testConst(){
		List<String> seatsList = new ArrayList<>();
		seatsList.add("a1");
		seatsList.add("a2");
		seatsList.add("a3");
		
		Ticket ticket = new Ticket(101,"Pathan","PVR",3,seatsList);
		
		assertEquals(ticket.getMovieName(), "Pathan");
		assertEquals(ticket.getSeats(), seatsList);
		assertEquals(ticket.getNumberOfTickets(),3);
		assertEquals(ticket.getTheatreName(), "PVR");
		assertEquals(ticket.getTicketId(), 101);
		
	}
	
}
