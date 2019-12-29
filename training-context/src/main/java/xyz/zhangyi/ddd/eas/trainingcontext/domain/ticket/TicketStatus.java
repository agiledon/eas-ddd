package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

public enum TicketStatus {
    Available, WaitForConfirm;

    public boolean isAvailable() {
        return this == Available;
    }
}