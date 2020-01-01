package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

public class Nominee {
    private String employeeId;
    private String name;
    private String email;

    public Nominee(String employeeId, String name, String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
    }

    public String employeeId() {
        return this.employeeId;
    }

    public String name() {
        return this.name;
    }

    public String email() {
        return email;
    }
}