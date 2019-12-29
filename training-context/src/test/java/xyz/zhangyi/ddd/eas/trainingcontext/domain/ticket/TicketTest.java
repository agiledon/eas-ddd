package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import org.junit.Before;
import org.junit.Test;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketTest {

    private String trainingId;
    private Candidate candidate;

    @Before
    public void setUp() {
        trainingId = "111011111111";
        candidate = new Candidate("200901010110", "Tom", "tom@eas.com", trainingId);
    }

    @Test
    public void should_throw_TicketException_given_ticket_is_not_AVAILABLE() {
        Ticket ticket = new Ticket(TicketId.next(), trainingId, TicketStatus.WaitForConfirm);

        assertThatThrownBy(() -> ticket.nominate(candidate))
                .isInstanceOf(TicketException.class)
                .hasMessageContaining("ticket is not available");
    }

    @Test
    public void ticket_status_should_be_WAIT_FOR_CONFIRM_after_ticket_was_nominated() {
        Ticket ticket = new Ticket(TicketId.next(), trainingId);

        ticket.nominate(candidate);

        assertThat(ticket.status()).isEqualTo(TicketStatus.WaitForConfirm);
        assertThat(ticket.nomineeId()).isEqualTo(candidate.employeeId());
    }
}