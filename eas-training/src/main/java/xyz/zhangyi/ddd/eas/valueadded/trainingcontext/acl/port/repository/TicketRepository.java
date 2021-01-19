package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repository;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.Ticket;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketStatus;

@Mapper
@Repository
@Port(PortType.Repository)
public interface TicketRepository {
    Optional<Ticket> ticketOf(TicketId ticketId, TicketStatus ticketStatus);
    void update(Ticket ticket);
    void add(Ticket ticket);
    void remove(Ticket ticket);
}