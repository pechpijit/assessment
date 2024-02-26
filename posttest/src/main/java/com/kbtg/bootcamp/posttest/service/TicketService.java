package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.request.TicketRequest;
import com.kbtg.bootcamp.posttest.response.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.response.TicketResponse;
import com.kbtg.bootcamp.posttest.response.UserTicketResponse;
import org.springframework.http.ResponseEntity;

public interface TicketService {

    public ResponseEntity<TicketResponse> getAllTicket();

    public ResponseEntity<TicketResponse> createTicket(TicketRequest request);

    public ResponseEntity<PurchaseTicketResponse> purchaseTicket(String userId, String ticketNumber);

    public ResponseEntity<UserTicketResponse> getAllTickerByUserId(String userId);

    public ResponseEntity<TicketResponse> removeTicket(String userId, String ticketNumber);
}
