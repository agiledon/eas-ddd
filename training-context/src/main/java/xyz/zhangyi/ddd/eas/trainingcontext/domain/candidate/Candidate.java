package xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate;

public class Candidate {
    private String employeeId;
    private String name;
    private String email;

    public Candidate(String EmployeeId, String name, String email) {
        this.employeeId = EmployeeId;
        this.name = name;
        this.email = email;
    }
}