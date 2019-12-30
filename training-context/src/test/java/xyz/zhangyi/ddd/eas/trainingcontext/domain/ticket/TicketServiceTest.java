package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;

import java.util.Optional;

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

        String trainingId = "111011111111";
        Candidate candidate = new Candidate("200901010110", "Tom", "tom@eas.com", trainingId);
        Nominator nominator = new Nominator("200901010007", "admin", "admin@eas.com", TrainingRole.Coordinator);

        assertThatThrownBy(() -> ticketService.nominate(ticketId, candidate, nominator))
                .isInstanceOf(TicketException.class)
                .hasMessageContaining(String.format("available ticket by id {%s} is not found", ticketId.id()));
        verify(mockTickRepo).ticketOf(ticketId, Available);
    }
}