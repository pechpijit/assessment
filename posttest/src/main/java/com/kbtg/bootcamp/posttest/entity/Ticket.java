package com.kbtg.bootcamp.posttest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "lottery")
public class Ticket {
    @Id
    @NotNull
    @Column(name = "ticket_number")
    private String ticketNumber;

    @NotNull
    @Column(name = "price")
    private Integer ticketPrice;

    @NotNull
    @Column(name = "amount")
    private Integer ticketAmount;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "created_at")
    private LocalDateTime createAt = LocalDateTime.now();

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
