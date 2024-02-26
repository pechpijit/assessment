package com.kbtg.bootcamp.posttest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TicketRequest {
    @NotBlank(message = "ticket is required.")
    @Size(min = 6, max = 6, message = "ticket must be 6 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "please enter 6 digits for {ticket} number only.")
    @JsonProperty("ticket")
    private String ticketNumber;

    @NotNull(message = "price is required")
    @JsonProperty("price")
    private Integer ticketPrice;

    @NotNull(message = "amount is required")
    @JsonProperty("amount")
    private Integer ticketAmount;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }
}
