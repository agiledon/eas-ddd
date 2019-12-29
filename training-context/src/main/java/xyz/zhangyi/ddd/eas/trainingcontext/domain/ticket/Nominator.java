package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

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
}