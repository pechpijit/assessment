package com.kbtg.bootcamp.posttest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseTicketResponse {
    @JsonProperty("id")
    private Integer tickerId;

    public PurchaseTicketResponse() {
    }

    public PurchaseTicketResponse(Integer tickerId) {
        this.tickerId = tickerId;
    }

    public Integer getTickerId() {
        return tickerId;
    }

    public void setTickerId(Integer tickerId) {
        this.tickerId = tickerId;
    }
}
