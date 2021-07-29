package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.south.port.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.tickethistory.TicketHistory;

@Mapper
@Repository
@Port(PortType.Repository)
public interface TicketHistoryRepository {
    Optional<TicketHistory> latest(TicketId ticketId);
    void add(TicketHistory ticketHistory);
    void deleteBy(TicketId ticketId);
}