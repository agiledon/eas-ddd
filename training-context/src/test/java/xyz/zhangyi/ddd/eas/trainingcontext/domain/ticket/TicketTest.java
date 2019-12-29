package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketTest {
    @Test
    public void should_throw_TicketException_given_ticket_is_not_AVAILABLE() {
        Ticket ticket = new Ticket(TicketId.next(), "111011111111", TicketStatus.WaitForConfirm);
        Candidate candidate = new Candidate("200901010110", "Tom", "tom@eas.com");

        assertThatThrownBy(() -> ticket.nominate(candidate))
                .isInstanceOf(TicketException.class)
                .hasMessageContaining("ticket is not available");
    }
}