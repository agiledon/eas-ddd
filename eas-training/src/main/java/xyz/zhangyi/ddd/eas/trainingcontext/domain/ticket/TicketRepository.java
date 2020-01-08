package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface TicketRepository {
    Optional<Ticket> ticketOf(TicketId ticketId, TicketStatus ticketStatus);
    void update(Ticket ticket);
    void add(Ticket ticket);
    void remove(Ticket ticket);
}