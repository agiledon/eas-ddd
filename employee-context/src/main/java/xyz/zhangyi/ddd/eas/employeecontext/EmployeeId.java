package xyz.zhangyi.ddd.eas.employeecontext;

import java.util.Objects;

public class EmployeeId {
    private final String id;

    public EmployeeId(String id) {
        this.id = id;
    }

    public String sequenceCode() {
        return id.substring(id.length() - 4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId that = (EmployeeId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Employee Id is %s", id);
    }
}
