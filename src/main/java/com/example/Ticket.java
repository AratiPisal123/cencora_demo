package com.example;
//Ticket.java


import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@ApplicationScoped
@Entity
@Table(name="ticket")
public class Ticket{
    @Id
    private Integer id;
    private String issue;
    private LocalDate raisedon;

    public Ticket() {}
    public Ticket(int id, String issue, LocalDate raisedon) {
        super();
        this.id = id;
        this.issue = issue;
        this.raisedon = raisedon;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIssue() {
        return issue;
    }
    public void setIssue(String issue) {
        this.issue = issue;
    }
    public LocalDate getRaisedon() {
        return raisedon;
    }
    public void setRaisedon(LocalDate raisedon) {
        this.raisedon = raisedon;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", issue=" + issue + ", raisedon=" + raisedon + "]";
    }

}




//TicketS