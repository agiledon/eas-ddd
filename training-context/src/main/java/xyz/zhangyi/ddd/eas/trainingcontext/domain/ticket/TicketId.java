package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import java.util.Objects;
import java.util.UUID;

public class TicketId {
    private String id;

    private TicketId(String id) {
        this.id = id;
    }

    public static TicketId next() {
        return new TicketId(UUID.randomUUID().toString());
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId ticketId = (TicketId) o;
        return id.equals(ticketId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}