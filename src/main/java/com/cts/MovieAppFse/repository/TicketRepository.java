package com.cts.MovieAppFse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cts.MovieAppFse.model.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Integer> {

}
