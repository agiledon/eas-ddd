package xyz.zhangyi.ddd.eas.employeecontext;

import xyz.zhangyi.ddd.eas.employeecontext.exceptions.InvalidEmployeeException;

import java.util.Objects;

public class Employee {
    private String name;
    private final IDCard idCard;
    private final Phone mobile;
    private final Gender gender;

    public Employee(String name, IDCard idCard, Phone mobile, Gender gender) {

        if (Objects.isNull(name)) throw new InvalidEmployeeException("Name should not be null");

        this.name = name;
        this.idCard = idCard;
        this.mobile = mobile;
        this.gender = gender;
    }
}
