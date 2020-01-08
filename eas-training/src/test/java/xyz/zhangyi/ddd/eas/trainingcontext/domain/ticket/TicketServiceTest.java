package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.CandidateRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistory;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistoryRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketStatus.Available;

public class TicketServiceTest {
    @Test
    public void should_throw_TicketException_if_available_ticket_not_found() {
        TicketId ticketId = TicketId.next();
        TicketRepository mockTickRepo = mock(TicketRepository.class);
        when(mockTickRepo.ticketOf(ticketId, Available)).thenReturn(Optional.empty());

        TicketService ticketService = new TicketService();
        ticketService.setTicketRepository(mockTickRepo);

        Candidate candidate = new Candidate("200901010110", "Tom", "tom@eas.com", TrainingId.next());
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);

        assertThatThrownBy(() -> ticketService.nominate(ticketId, nominator, candidate))
                .isInstanceOf(TicketException.class)
                .hasMessageContaining(String.format("available ticket by id {%s} is not found", ticketId.value()));
        verify(mockTickRepo).ticketOf(ticketId, Available);
    }

    @Test
    public void should_nominate_candidate_for_specific_ticket() {
        // given
        TrainingId trainingId = TrainingId.next();
        TicketId ticketId = TicketId.next();
        Ticket ticket = new Ticket(TicketId.next(), trainingId, Available);

        TicketRepository mockTickRepo = mock(TicketRepository.class);
        when(mockTickRepo.ticketOf(ticketId, Available)).thenReturn(Optional.of(ticket));

        TicketHistoryRepository mockTicketHistoryRepo = mock(TicketHistoryRepository.class);
        CandidateRepository mockCandidateRepo = mock(CandidateRepository.class);

        TicketService ticketService = new TicketService();
        ticketService.setTicketRepository(mockTickRepo);
        ticketService.setTicketHistoryRepository(mockTicketHistoryRepo);
        ticketService.setCandidateRepository(mockCandidateRepo);

        Candidate candidate = new Candidate("200901010110", "Tom", "tom@eas.com", trainingId);
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);

        // when
        Ticket nominatedTicket = ticketService.nominate(ticketId, nominator, candidate);

        // then
        assertThat(nominatedTicket.status().isWaitForConfirm()).isTrue();
        verify(mockTickRepo).ticketOf(ticketId, Available);
        verify(mockTickRepo).update(ticket);
        verify(mockTicketHistoryRepo).add(isA(TicketHistory.class));
        verify(mockCandidateRepo).remove(candidate);
    }
}