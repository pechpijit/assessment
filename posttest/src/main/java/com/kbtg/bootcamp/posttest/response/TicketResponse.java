package com.kbtg.bootcamp.posttest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ticket")
    private String ticketNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tickets")
    private List<String> ticketList;

    public TicketResponse() {
    }

    public TicketResponse(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public TicketResponse(List<String> ticketList) {
        this.ticketList = ticketList;
    }


    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public List<String> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<String> ticketList) {
        this.ticketList = ticketList;
    }
}
