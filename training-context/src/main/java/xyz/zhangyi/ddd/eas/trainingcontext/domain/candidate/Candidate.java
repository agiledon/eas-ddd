package xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.Nominee;
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

    public String name() {
        return this.name;
    }

    public String email() {
        return email;
    }

    public TrainingId trainingId() {
        return trainingId;
    }

    public TicketOwner toOwner() {
        return new TicketOwner(employeeId, TicketOwnerType.Nominee);
    }

    public Nominee toNominee() {
        return new Nominee(employeeId, name, email);
    }
}