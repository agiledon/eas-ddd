package xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistory;

@Mapper
@Repository
public interface TicketHistoryRepository {
    Optional<TicketHistory> latest(TicketId ticketId);
    void add(TicketHistory ticketHistory);
    void deleteBy(TicketId ticketId);
}