package xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketStatus;

import java.util.Objects;

public class StateTransit {
    private TicketStatus from;
    private TicketStatus to;

    private StateTransit() {
    }

    public StateTransit(TicketStatus from, TicketStatus to) {
        this.from = from;
        this.to = to;
    }

    public static StateTransit from(TicketStatus from) {
        StateTransit stateTransit = new StateTransit();
        stateTransit.from = from;
        return stateTransit;
    }

    public StateTransit to(TicketStatus to) {
        this.to = to;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTransit that = (StateTransit) o;
        return from == that.from &&
                to == that.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}