package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket;

import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.tickethistory.Operator;

import java.util.Objects;

public class Nominator {
    private String employeeId;
    private String name;
    private String email;
    private TrainingRole role;

    public Nominator(String employeeId, String name, String email, TrainingRole role) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String employeeId() {
        return this.employeeId;
    }

    public String name() {
        return this.name;
    }

    public Operator toOperator() {
        return new Operator(employeeId(), name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nominator nominator = (Nominator) o;
        return employeeId.equals(nominator.employeeId) &&
                name.equals(nominator.name) &&
                email.equals(nominator.email) &&
                role == nominator.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, email, role);
    }
}