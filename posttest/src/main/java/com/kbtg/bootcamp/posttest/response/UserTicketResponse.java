package com.kbtg.bootcamp.posttest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserTicketResponse {
    @JsonProperty("tickets")
    private List<String> ticketList;
    private int count;
    private Integer cost;

    public UserTicketResponse() {
    }

    public UserTicketResponse(List<String> ticketList, int count, Integer cost) {
        this.ticketList = ticketList;
        this.count = count;
        this.cost = cost;
    }

    public List<String> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<String> ticketList) {
        this.ticketList = ticketList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
