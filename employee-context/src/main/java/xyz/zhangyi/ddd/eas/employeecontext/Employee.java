package xyz.zhangyi.ddd.eas.employeecontext;

import com.google.common.base.Strings;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import java.util.Objects;

public class Employee {
    private String name;
    private final IDCard idCard;
    private final Phone mobile;
    private final Gender gender;

    public Employee(String name, IDCard idCard, Phone mobile) {
        this.name = validateName(name);
        this.idCard = requireNonNull(idCard, "ID Card should not be null");
        this.mobile = requireNonNull(mobile, "Mobile Phone should not be null");
        this.gender = idCard.isMale() ? Gender.Male : Gender.Female;
    }

    public boolean isMale() {
        return gender.isMale();
    }

    public boolean isFemale() {
        return gender.isFemale();
    }

    private String validateName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new InvalidEmployeeException("Name should not be null or empty");
        }
        return name;
    }

    private <T> T requireNonNull(T obj, String errorMessage) {
        if (Objects.isNull(obj)) {
            throw new InvalidEmployeeException(errorMessage);
        }
        return obj;
    }
}
