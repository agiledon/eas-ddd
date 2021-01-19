package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.tickethistory;

import xyz.zhangyi.ddd.core.stereotype.Aggregate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.TicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Aggregate
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
        this(UUID.randomUUID().toString(), ticketId, owner, stateTransit, operationType, operatedBy, operatedAt);
    }

    public TicketHistory(String id,
                         TicketId ticketId,
                         TicketOwner owner,
                         StateTransit stateTransit,
                         OperationType operationType,
                         Operator operatedBy,
                         LocalDateTime operatedAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.owner = owner;
        this.stateTransit = stateTransit;
        this.operationType = operationType;
        this.operatedBy = operatedBy;
        this.operatedAt = operatedAt;
    }

    public TicketHistory(String id,
                         TicketId ticketId,
                         String ownerId,
                         TicketOwnerType ownerType,
                         TicketStatus fromStatus,
                         TicketStatus toStatus,
                         OperationType operationType,
                         String operatorId,
                         String operatorName,
                         LocalDateTime operatedAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.owner = new TicketOwner(ownerId, ownerType);
        this.stateTransit = StateTransit.from(fromStatus).to(toStatus);
        this.operationType = operationType;
        this.operatedBy = new Operator(operatorId, operatorName);
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