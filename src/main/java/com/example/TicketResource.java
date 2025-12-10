package com.example;



import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tickets")
public class TicketResource {

    @Inject
    private TicketService ticketService;

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTickets() {
        List<Ticket> list = ticketService.getAllTickets();
        return Response.ok(list).build();
    }
    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") int id, Ticket ticket) {
        Ticket updated = ticketService.updateTicket(id, ticket);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(updated).build();
    }


    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketById(@PathParam("id") int id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return Response.ok(ticket.isPresent() ? ticket.get() : Response.Status.NO_CONTENT).build();
    }

    @Path("/post")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTicket(Ticket ticket) {
        return Response.ok(ticketService.saveTicket(ticket)).build();
    }
    @Path("/delete/{id}")
    @Transactional
    public Response delete(@PathParam("id") int id) {
        boolean deleted = ticketService.deleteTicket(id);

        return deleted
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
