package xyz.zhangyi.ddd.eas.employeecontext;

import com.google.common.base.Strings;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeIdException;

import java.util.Objects;

public class EmployeeId {
    private final String id;

    public EmployeeId(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new InvalidEmployeeIdException("Employee id should not be null or empty.");
        }

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
