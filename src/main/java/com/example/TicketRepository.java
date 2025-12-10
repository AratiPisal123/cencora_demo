package com.example;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
//TicketRepository.java


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<Ticket> {
}