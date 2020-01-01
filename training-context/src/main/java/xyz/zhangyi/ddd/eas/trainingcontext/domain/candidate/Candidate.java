package xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketOwner;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketOwnerType;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

public class Candidate {
    private String employeeId;
    private String name;
    private String email;
    private TrainingId trainingId;

    public Candidate(String EmployeeId, String name, String email, TrainingId trainingId) {
        this.employeeId = EmployeeId;
        this.name = name;
        this.email = email;
        this.trainingId = trainingId;
    }

    public String employeeId() {
        return employeeId;
    }

    public TicketOwner toOwner() {
        return new TicketOwner(employeeId(), TicketOwnerType.Nominee);
    }
}