package xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TicketHistoryRepository {
    void add(TicketHistory ticketHistory);
}