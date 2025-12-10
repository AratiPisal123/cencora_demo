package com.example;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
class TicketResourceTest {

    @Inject
    TicketService ticketService;

    @BeforeEach
    void setup() {
        // Ensure ticket with ID=1 exists
        if (ticketService.getTicketById(1).isEmpty()) {
            Ticket t = new Ticket(1, "Sample Issue", LocalDate.now());
            ticketService.saveTicket(t);
        }

        // Ensure ticket with ID=101 exists for update/delete tests
        if (ticketService.getTicketById(101).isEmpty()) {
            Ticket t = new Ticket(101, "Ticket for Update/Delete", LocalDate.now());
            ticketService.saveTicket(t);
        }
    }

    @Test
    void testGetAllTicketsEndPoint() {
        List<Ticket> expected = ticketService.getAllTickets();

        List<Ticket> actual =
                given()
                        .when().get("/tickets/get")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(new TypeRef<List<Ticket>>() {
                        });

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
    }

    @Test
    void testGetTicketByIdEndPoint() {
        Ticket expected = ticketService.getTicketById(1).orElseThrow();

        Ticket actual =
                given()
                        .when().get("/tickets/get/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Ticket.class);

        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void saveTicket() {
        Ticket testTicket = new Ticket(102, "Sample ticket", LocalDate.now());

        Ticket actual =
                given()
                        .contentType("application/json")
                        .body(testTicket)
                        .when()
                        .post("/tickets/post")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Ticket.class);

        assertEquals(testTicket.getId(), actual.getId());
    }

    // --------------------- UPDATE TEST ---------------------
    @Test
    void updateTicket() {
        Ticket updatedTicket = new Ticket(101, "Updated Title", LocalDate.now());

        Ticket actual =
                given()
                        .contentType("application/json")
                        .body(updatedTicket)
                        .when()
                        .put("/tickets/update/101")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Ticket.class);

        assertEquals(updatedTicket.getId(), actual.getId());
//        assertEquals("Updated Title", actual.getTitle());
    }

    // --------------------- DELETE TEST ---------------------
    @Test
    void deleteTicket() {
        given()
                .when()
                .delete("/tickets/delete/101")
                .then()
                .statusCode(200);

        // Verify it's deleted
        assertFalse(ticketService.getTicketById(101).isPresent());
    }
}
