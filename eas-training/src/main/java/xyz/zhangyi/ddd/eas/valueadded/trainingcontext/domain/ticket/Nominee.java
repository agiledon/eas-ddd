package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nominee nominee = (Nominee) o;
        return employeeId.equals(nominee.employeeId) &&
                name.equals(nominee.name) &&
                email.equals(nominee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, email);
    }
}