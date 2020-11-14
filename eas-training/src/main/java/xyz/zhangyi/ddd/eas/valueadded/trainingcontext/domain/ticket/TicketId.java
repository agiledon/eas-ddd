package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket;

import java.util.Objects;
import java.util.UUID;

public class TicketId {
    private String value;

    private TicketId(String value) {
        this.value = value;
    }

    public static TicketId next() {
        return new TicketId(UUID.randomUUID().toString());
    }

    public static TicketId from(String value) {
        return new TicketId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId ticketId = (TicketId) o;
        return value.equals(ticketId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}