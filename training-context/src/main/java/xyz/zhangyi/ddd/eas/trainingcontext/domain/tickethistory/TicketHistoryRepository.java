package xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory;

import org.springframework.stereotype.Repository;

@Repository
public interface TicketHistoryRepository {
    void add(TicketHistory ticketHistory);
}