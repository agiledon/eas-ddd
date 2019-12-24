package xyz.zhangyi.ddd.eas.employeecontext;

import com.google.common.base.Strings;
import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import java.util.Objects;

public class Employee {
    private String name;
    private final IDCard idCard;
    private final Phone mobile;
    private final Gender gender;

    public Employee(String name, IDCard idCard, Phone mobile, Gender gender) {
        if (Strings.isNullOrEmpty(name)) throw new InvalidEmployeeException("Name should not be null or empty");
        if (Objects.isNull(idCard)) throw new InvalidEmployeeException("ID Card should not be null");

        this.name = name;
        this.idCard = idCard;
        this.mobile = mobile;
        this.gender = gender;
    }
}
