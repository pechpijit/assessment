package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.response.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.response.TicketResponse;
import com.kbtg.bootcamp.posttest.response.UserTicketResponse;
import com.kbtg.bootcamp.posttest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final TicketService ticketService;

    @Autowired
    public UserController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{userId}/lotteries/{ticketNumber}")
    public ResponseEntity<PurchaseTicketResponse> purchaseTicket(@PathVariable("userId") String userId, @PathVariable("ticketNumber") String ticketNumber) {
        return ticketService.purchaseTicket(userId, ticketNumber);
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<UserTicketResponse> getAllTickerByUserId(@PathVariable("userId") String userId) {
        return ticketService.getAllTickerByUserId(userId);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketNumber}")
    public ResponseEntity<TicketResponse> removeUserTicketByUserIdAndTicketNumber(@PathVariable("userId") String userId, @PathVariable("ticketNumber") String ticketNumber) {
        return ticketService.removeTicket(userId, ticketNumber);
    }
}
