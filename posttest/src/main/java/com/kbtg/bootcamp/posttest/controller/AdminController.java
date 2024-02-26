package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.request.TicketRequest;
import com.kbtg.bootcamp.posttest.response.TicketResponse;
import com.kbtg.bootcamp.posttest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final TicketService ticketService;

    @Autowired
    public AdminController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lotteries")
    public ResponseEntity<TicketResponse> lotteries(@Validated @RequestBody TicketRequest request) {
        return ticketService.createTicket(request);
    }
}
