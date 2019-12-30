package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> ticketOf(TicketId ticketId, TicketStatus ticketStatus);
    void update(Ticket ticket);
}