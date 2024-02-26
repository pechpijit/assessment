package com.kbtg.bootcamp.posttest.repository;

import com.kbtg.bootcamp.posttest.entity.UserTicket;
import com.kbtg.bootcamp.posttest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Integer> {

    List<UserTicket> findAllByUser(Users user);

    @Modifying
    @Transactional
    void deleteByTicketId(Integer ticketId);

    @Query("SELECT ut FROM UserTicket ut WHERE ut.user = :user AND ut.ticket.ticketNumber = :ticketNumber")
    UserTicket findTicketByUserIdAndTickerNumber(@Param("user") Users user, @Param("ticketNumber") String ticketNumber);
}
