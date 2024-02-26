package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.response.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.response.TicketResponse;
import com.kbtg.bootcamp.posttest.response.UserTicketResponse;
import com.kbtg.bootcamp.posttest.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mockMvc;
    @Mock
    TicketService ticketService;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(ticketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when user purchase lottery on POST: /users/:userId/lotteries/:ticketNumber should return status 200 and body contain ticket id.")
    void purchaseTicket() throws Exception {
        PurchaseTicketResponse ticketResponse = new PurchaseTicketResponse(1);

        when(ticketService.purchaseTicket("1111111111", "111111")).thenReturn(new ResponseEntity<>(ticketResponse, HttpStatus.CREATED));

        mockMvc.perform(post("/users/1111111111/lotteries/111111"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when perform on GET: /{userId}/lotteries should return status 200 and body contain list of lotteries from userId.")
    void viewLotteriesByUser() throws Exception {
        List<String> ticketNumbers = List.of("111111");

        UserTicketResponse userTicketResponse = new UserTicketResponse();
        userTicketResponse.setTicketList(List.of("111111"));
        userTicketResponse.setCost(80);
        userTicketResponse.setCount(1);

        when(ticketService.getAllTickerByUserId("1111111111")).thenReturn(new ResponseEntity<>(userTicketResponse, HttpStatus.OK));

        mockMvc.perform(get("/users/1111111111/lotteries"))
                .andExpect(jsonPath("$.tickets", is(ticketNumbers)))
                .andExpect(jsonPath("$.cost", is(80)))
                .andExpect(jsonPath("$.count", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when perform on GET: /{userId}/lotteries/{ticketId} should return status 200 and body contain list of lotteries from userId.")
    void deleteLottery() throws Exception {
        TicketResponse ticketResponse = new TicketResponse("111111");

        when(ticketService.removeTicket("1111111111","1")).thenReturn(new ResponseEntity<>(ticketResponse, HttpStatus.OK));

        mockMvc.perform(delete("/users/1111111111/lotteries/1"))
                .andExpect(jsonPath("$.ticket", is("111111")))
                .andExpect(status().isOk());
    }
}