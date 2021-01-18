package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.tickethistory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories.TicketHistoryRepository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class TicketHistoryRepositoryIT {
    @Autowired
    private TicketHistoryRepository ticketHistoryRepository;
    private final TicketId ticketId = TicketId.from("18e38931-822e-4012-a16e-ac65dfc56f8a");

    @Before
    public void setup() {
        ticketHistoryRepository.deleteBy(ticketId);

        StateTransit availableToWaitForConfirm = StateTransit.from(TicketStatus.Available).to(TicketStatus.WaitForConfirm);
        LocalDateTime oldTime = LocalDateTime.of(2020, 1, 1, 12, 0, 0);
        TicketHistory oldHistory = createTicketHistory(availableToWaitForConfirm, oldTime);
        ticketHistoryRepository.add(oldHistory);

        StateTransit toConfirm = StateTransit.from(TicketStatus.WaitForConfirm).to(TicketStatus.Confirm);
        LocalDateTime newTime = LocalDateTime.of(2020, 1, 1, 13, 0, 0);
        TicketHistory newHistory = createTicketHistory(toConfirm, newTime);
        ticketHistoryRepository.add(newHistory);
    }

    @Test
    public void should_return_latest_one() {
        Optional<TicketHistory> latest = ticketHistoryRepository.latest(ticketId);

        assertThat(latest.isPresent()).isTrue();
        assertThat(latest.get().getStateTransit()).isEqualTo(StateTransit.from(TicketStatus.WaitForConfirm).to(TicketStatus.Confirm));
//        LocalDateTime expectedDateTime = LocalDateTime.of(2020, 1, 1, 13, 0, 0);
//        assertThat(latest.get().operatedAt().plusHours(8)).isEqualTo(expectedDateTime);
    }

    private TicketHistory createTicketHistory(StateTransit stateTransit, LocalDateTime operatedAt) {
        String ownerId = "201101011100";
        TicketOwner owner = new TicketOwner(ownerId, TicketOwnerType.Nominee);
        String operatorId = "200101010100";
        Operator operator = new Operator(operatorId, "admin");
        return new TicketHistory(ticketId, owner, stateTransit, OperationType.Nomination, operator, operatedAt);
    }
}