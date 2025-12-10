package com.example;



import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TicketService {

    @Inject
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll().list();
    }

    @Transactional
    public Ticket updateTicket(int id, Ticket updated) {
        Ticket existing = ticketRepository.findById((long) id);
        if (existing == null) return null;

        existing.setIssue(updated.getIssue());
        existing.setRaisedon(updated.getRaisedon());

        return existing;
    }
    @Transactional
    public boolean deleteTicket(int id) {
        return ticketRepository.deleteById((long) id);
    }


    public Optional<Ticket> getTicketById(int id) {
        Optional<Ticket> ticket = ticketRepository.findByIdOptional((long) id);
        return ticket.filter(t -> t.getId() == id);
    }

    @Transactional          // ✅ REQUIRED
    public Ticket saveTicket(Ticket ticket) {
        ticketRepository.persist(ticket);
        return ticket;
    }
}
