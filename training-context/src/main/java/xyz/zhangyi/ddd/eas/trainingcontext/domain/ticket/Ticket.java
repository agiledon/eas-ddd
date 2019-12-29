package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.*;

import java.time.LocalDateTime;

public class Ticket {
    private TicketId ticketId;
    private String trainingId;
    private TicketStatus ticketStatus;
    private String nomineeId;

    public Ticket(TicketId ticketId, String trainingId) {
        this(ticketId, trainingId, TicketStatus.Available);
    }

    public Ticket(TicketId ticketId, String trainingId, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.trainingId = trainingId;
        this.ticketStatus = ticketStatus;
    }

    public TicketHistory nominate(Candidate candidate, Nominator nominator) {
        if (!ticketStatus.isAvailable()) {
            throw new TicketException("ticket is not available, cannot be nominated.");
        }

        this.ticketStatus = TicketStatus.WaitForConfirm;
        this.nomineeId = candidate.employeeId();

        return new TicketHistory(ticketId,
                new TicketOwner(candidate.employeeId(), TicketOwnerType.Nominee),
                StateTransit.from(TicketStatus.Available).to(this.ticketStatus),
                OperationType.Nomination,
                new Operator(nominator.employeeId(), nominator.name()),
                LocalDateTime.now());
    }

    public TicketStatus status() {
        return ticketStatus;
    }

    public String nomineeId() {
        return nomineeId;
    }

    public TicketId id() {
        return this.ticketId;
    }
}
