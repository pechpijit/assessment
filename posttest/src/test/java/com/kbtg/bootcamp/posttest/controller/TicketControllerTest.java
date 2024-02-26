package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.response.TicketResponse;
import com.kbtg.bootcamp.posttest.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    MockMvc mockMvc;
    @Mock
    TicketService ticketService;

    @BeforeEach
    void setUp() {
        TicketController ticketController = new TicketController(ticketService);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when perform on GET: /lotteries should return Ticket List")
    void viewWallet() throws Exception {
        List<String> ticketNumbers = List.of("111111", "222222", "333333", "444444", "555555");

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketList(ticketNumbers);

        when(ticketService.getAllTicket()).thenReturn(new ResponseEntity<>(ticketResponse, HttpStatus.OK));

        mockMvc.perform(get("/lotteries"))
                .andExpect(jsonPath("$.tickets", is(ticketNumbers)))
                .andExpect(status().isOk());
    }
}