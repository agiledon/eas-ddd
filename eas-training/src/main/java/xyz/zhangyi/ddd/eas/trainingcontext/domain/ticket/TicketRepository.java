package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TicketRepository {
    Optional<Ticket> ticketOf(TicketId ticketId, TicketStatus ticketStatus);
    void update(Ticket ticket);
    void add(Ticket ticket);
    void remove(Ticket ticket);
}