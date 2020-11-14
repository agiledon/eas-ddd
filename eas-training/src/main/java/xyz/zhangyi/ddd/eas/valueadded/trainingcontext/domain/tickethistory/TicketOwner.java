package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.tickethistory;

import java.util.Objects;

public class TicketOwner {
    private String employeeId;
    private TicketOwnerType ownerType;

    public TicketOwner(String employeeId, TicketOwnerType ownerType) {
        this.employeeId = employeeId;
        this.ownerType = ownerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketOwner that = (TicketOwner) o;
        return employeeId.equals(that.employeeId) &&
                ownerType == that.ownerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, ownerType);
    }
}