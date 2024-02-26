package com.kbtg.bootcamp.posttest.service.impl;

import com.kbtg.bootcamp.posttest.entity.Ticket;
import com.kbtg.bootcamp.posttest.entity.UserTicket;
import com.kbtg.bootcamp.posttest.entity.Users;
import com.kbtg.bootcamp.posttest.exception.DuplicationException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.repository.TicketRepository;
import com.kbtg.bootcamp.posttest.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.repository.UsersRepository;
import com.kbtg.bootcamp.posttest.request.TicketRequest;
import com.kbtg.bootcamp.posttest.response.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.response.TicketResponse;
import com.kbtg.bootcamp.posttest.response.UserTicketResponse;
import com.kbtg.bootcamp.posttest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.kbtg.bootcamp.posttest.Constant.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UsersRepository usersRepository;
    private final UserTicketRepository userTicketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UsersRepository usersRepository, UserTicketRepository userTicketRepository) {
        this.ticketRepository = ticketRepository;
        this.usersRepository = usersRepository;
        this.userTicketRepository = userTicketRepository;
    }

    @Override
    public ResponseEntity<TicketResponse> getAllTicket() {
        List<String> ticketList = ticketRepository.findAll().stream().map(Ticket::getTicketNumber).toList();

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketList(ticketList);

        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketResponse> createTicket(TicketRequest request) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(request.getTicketNumber());
        if (optionalTicket.isPresent()) {
            throw new DuplicationException(String.format(TICKET_NUMBER_ALREADY_EXISTS, request.getTicketNumber()));
        }

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(request.getTicketNumber());
        ticket.setTicketPrice(request.getTicketPrice());
        ticket.setTicketAmount(request.getTicketAmount());

        ticketRepository.save(ticket);

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketNumber(ticket.getTicketNumber());

        return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<PurchaseTicketResponse> purchaseTicket(String userId, String ticketNumber) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, userId)));

        Ticket ticket = ticketRepository.findById(ticketNumber)
                .orElseThrow(() -> new NotFoundException(String.format(TICKET_NUMBER_NOT_FOUND, ticketNumber)));

        if (ticket.getTicketAmount() <= 0) {
            throw new NotFoundException(String.format(TICKER_SOLD_OUT, ticketNumber));
        }

        ticket.setTicketAmount(ticket.getTicketAmount() - 1);
        ticketRepository.save(ticket);

        UserTicket userTicket = new UserTicket();
        userTicket.setUser(user);
        userTicket.setTicket(ticket);

        UserTicket userTicketSave = userTicketRepository.save(userTicket);

        PurchaseTicketResponse purchaseTicketResponse = new PurchaseTicketResponse();
        purchaseTicketResponse.setTickerId(userTicketSave.getTicketId());

        return new ResponseEntity<>(purchaseTicketResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserTicketResponse> getAllTickerByUserId(String userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, userId)));

        List<UserTicket> userTickets = userTicketRepository.findAllByUser(user);
        List<String> tickets = userTickets.stream().map(UserTicket::getTicket).map(Ticket::getTicketNumber).toList();
        int cost = userTickets.stream().map(UserTicket::getTicket).map(Ticket::getTicketPrice).reduce(0, Integer::sum);
        int count = userTickets.size();

        UserTicketResponse userTicketResponse = new UserTicketResponse();
        userTicketResponse.setTicketList(tickets);
        userTicketResponse.setCost(cost);
        userTicketResponse.setCount(count);

        return new ResponseEntity<>(userTicketResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<TicketResponse> removeTicket(String userId, String ticketNumber) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, userId)));

        UserTicket userTicket = userTicketRepository.findTicketByUserIdAndTickerNumber(user, ticketNumber);

        if (userTicket == null) {
            throw new NotFoundException(String.format(TICKET_NUMBER_NOT_FOUND_BY_USERID, ticketNumber, userId));
        }

        userTicketRepository.deleteByTicketId(userTicket.getTicketId());

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketNumber);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setTicketAmount(ticket.getTicketAmount() + 1);
            ticketRepository.save(ticket);
        }

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketNumber(userTicket.getTicket().getTicketNumber());

        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    }
}
