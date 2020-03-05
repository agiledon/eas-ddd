package xyz.zhangyi.ddd.eas.trainingcontext.ohs.local.appservices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationException;
import xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories.TicketRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.ohs.local.appservices.NominationAppService;
import xyz.zhangyi.ddd.eas.trainingcontext.ohs.local.pl.NominationRequest;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.course.CourseId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.*;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.StateTransit;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistory;
import xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories.TicketHistoryRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories.TrainingRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;
import xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories.ValidDateRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateType;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketStatus.Available;
import static xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketStatus.WaitForConfirm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class NominationAppServiceIT {
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ValidDateRepository validDateRepository;
    @Autowired
    private TicketHistoryRepository ticketHistoryRepository;

    @Autowired
    private NominationAppService nominationAppService;

    private final TrainingId trainingId = TrainingId.from("trc10a32-7585-4fc9-aa94-7329d9ab2a81");
    private final CourseId courseId = CourseId.from("co31e26a-9b16-48a8-8068-3f601f566295");
    private final TicketId ticketId = TicketId.from("ti1cdc3c-667c-4bb0-8088-201d8dff3c82");
    private Training training;
    private Ticket ticket;
    private ValidDate validDate;
    private final String candidateId = "201905051000";

    @Before
    public void setup() {
        training = createTraining();
        ticket = createTicket();
        validDate = createValidDate();

        // clean dirty data;
        trainingRepository.remove(training);
        ticketRepository.remove(ticket);
        validDateRepository.remove(validDate);
        ticketHistoryRepository.deleteBy(ticketId);

        // prepare new data;
        trainingRepository.add(this.training);
        ticketRepository.add(ticket);
        validDateRepository.add(validDate);
    }

    @Test
    public void should_nominate_candidate_to_nominee() {
        // given
        NominationRequest nominationRequest = createNominationRequest();

        // when
        nominationAppService.nominate(nominationRequest);

        // then
        Optional<Ticket> optionalAvailableTicket = ticketRepository.ticketOf(ticketId, Available);
        assertThat(optionalAvailableTicket.isPresent()).isFalse();

        Optional<Ticket> optionalConfirmedTicket = ticketRepository.ticketOf(ticketId, TicketStatus.WaitForConfirm);
        assertThat(optionalConfirmedTicket.isPresent()).isTrue();
        Ticket ticket = optionalConfirmedTicket.get();
        assertThat(ticket.id()).isEqualTo(ticketId);
        assertThat(ticket.trainingId()).isEqualTo(trainingId);
        assertThat(ticket.status()).isEqualTo(TicketStatus.WaitForConfirm);
        assertThat(ticket.nomineeId()).isEqualTo(candidateId);

        Optional<TicketHistory> optionalTicketHistory = ticketHistoryRepository.latest(ticketId);
        assertThat(optionalTicketHistory.isPresent()).isTrue();
        TicketHistory ticketHistory = optionalTicketHistory.get();
        assertThat(ticketHistory.ticketId()).isEqualTo(ticketId);
        assertThat(ticketHistory.getStateTransit()).isEqualTo(StateTransit.from(Available).to(WaitForConfirm));
    }

    @Test
    public void should_rollback_if_DomainException_had_been_thrown() {
        // given
        NominationRequest nominationRequest = createNominationRequest();

        // removing valid date in order to throw DomainException
        validDateRepository.remove(validDate);

        // when
        try {
            nominationAppService.nominate(nominationRequest);
        } catch (ApplicationException e) {
            // then
            Optional<Ticket> optionalAvailableTicket = ticketRepository.ticketOf(ticketId, Available);
            assertThat(optionalAvailableTicket.isPresent()).isTrue();
            Ticket ticket = optionalAvailableTicket.get();
            assertThat(ticket.id()).isEqualTo(ticketId);
            assertThat(ticket.trainingId()).isEqualTo(trainingId);
            assertThat(ticket.status()).isEqualTo(Available);
            assertThat(ticket.nomineeId()).isEqualTo(null);
        }
    }

    private Training createTraining() {
        LocalDateTime beginTime = LocalDateTime.of(2020, 9, 2, 9, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 9, 3, 17, 0, 0);
        return new Training(trainingId, "DDD", "DDD by ZhangYi", beginTime, endTime, "Beijing Room", courseId);
    }

    private Ticket createTicket() {
        return new Ticket(ticketId, trainingId);
    }

    private ValidDate createValidDate() {
        LocalDateTime deadline = LocalDateTime.of(2020, 7, 10, 0, 0, 0);
        return new ValidDate(trainingId, deadline, ValidDateType.PODeadline);
    }

    private NominationRequest createNominationRequest() {
        String ticketId = this.ticketId.value();
        String trainingId = this.trainingId.value();
        String candidateName = "guo jin";
        String candidateEmail = "jin.guo@eas.com";
        String nominatorId = "201001010111";
        String nominatorName = "ma yu";
        String nominatorEmail = "yu.ma@eas.com";
        TrainingRole nominatorRole = TrainingRole.Coordinator;

        return new NominationRequest(
                ticketId,
                trainingId,
                candidateId,
                candidateName,
                candidateEmail,
                nominatorId,
                nominatorName,
                nominatorEmail,
                nominatorRole);
    }
}