package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import java.util.UUID;

public class TicketId {
    private String id;

    private TicketId(String id) {
        this.id = id;
    }

    public static TicketId next() {
        return new TicketId(UUID.randomUUID().toString());
    }
}