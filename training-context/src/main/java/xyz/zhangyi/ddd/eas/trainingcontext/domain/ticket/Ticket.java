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
        validateTicketStatus();
        doNomination(candidate);
        return generateHistory(candidate, nominator);
    }

    private void validateTicketStatus() {
        if (!ticketStatus.isAvailable()) {
            throw new TicketException("ticket is not available, cannot be nominated.");
        }
    }

    private void doNomination(Candidate candidate) {
        this.ticketStatus = TicketStatus.WaitForConfirm;
        this.nomineeId = candidate.employeeId();
    }

    private TicketHistory generateHistory(Candidate candidate, Nominator nominator) {
        return new TicketHistory(ticketId,
                candidate.toOwner(),
                transitState(),
                OperationType.Nomination,
                nominator.toOperator(),
                LocalDateTime.now());
    }

    private StateTransit transitState() {
        return StateTransit.from(TicketStatus.Available).to(this.ticketStatus);
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

    public String url() {
        return String.format("http://www.eas.com/eas/tickets/%s", this.id().value());
    }
}