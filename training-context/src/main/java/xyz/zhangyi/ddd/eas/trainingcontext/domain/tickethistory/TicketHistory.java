package xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketId;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketHistory {
    private String id;
    private TicketId ticketId;
    private TicketOwner owner;
    private StateTransit stateTransit;
    private OperationType operationType;
    private Operator operatedBy;
    private LocalDateTime operatedAt;

    public TicketHistory(TicketId ticketId,
                         TicketOwner owner,
                         StateTransit stateTransit,
                         OperationType operationType,
                         Operator operatedBy,
                         LocalDateTime operatedAt) {
        this.id = UUID.randomUUID().toString();
        this.ticketId = ticketId;
        this.owner = owner;
        this.stateTransit = stateTransit;
        this.operationType = operationType;
        this.operatedBy = operatedBy;
        this.operatedAt = operatedAt;
    }

    public TicketId ticketId() {
        return this.ticketId;
    }

    public TicketOwner getTicketOwner() {
        return this.owner;
    }

    public StateTransit getStateTransit() {
        return this.stateTransit;
    }


    public OperationType operationType() {
        return this.operationType;
    }

    public Operator operatedBy() {
        return this.operatedBy;
    }

    public LocalDateTime operatedAt() {
        return this.operatedAt;
    }
}