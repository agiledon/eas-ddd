package xyz.zhangyi.ddd.eas.employeecontext;

import com.google.common.base.Strings;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Employee {
    private String name;
    private final IDCard idCard;
    private final Phone mobile;
    private final Gender gender;
    private final LocalDateTime onBoardingDate;

    public Employee(String name, IDCard idCard, Phone mobile) {
        this(name, idCard, mobile, LocalDateTime.now());
    }

    public Employee(String name, IDCard idCard, Phone mobile, LocalDateTime onBoardingDate) {
        this.name = validateName(name);
        this.idCard = requireNonNull(idCard, "ID Card should not be null");
        this.mobile = requireNonNull(mobile, "Mobile Phone should not be null");
        this.gender = idCard.isMale() ? Gender.Male : Gender.Female;
        this.onBoardingDate = onBoardingDate;
    }

    public boolean isMale() {
        return gender.isMale();
    }

    public boolean isFemale() {
        return gender.isFemale();
    }

    public LocalDateTime onBoardingDate() {
        return this.onBoardingDate;
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
