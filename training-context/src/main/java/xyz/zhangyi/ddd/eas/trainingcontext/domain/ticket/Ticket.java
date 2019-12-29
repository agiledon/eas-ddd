package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistory;

public class Ticket {
    private TicketId ticketId;
    private String trainingId;
    private TicketStatus ticketStatus;

    public Ticket(TicketId ticketId, String trainingId, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.trainingId = trainingId;
        this.ticketStatus = ticketStatus;
    }

    public TicketHistory nominate(Candidate candidate) {
        if (!ticketStatus.isAvailable()) {
            throw new TicketException("ticket is not available, cannot be nominated.");
        }
        return null;
    }
}
