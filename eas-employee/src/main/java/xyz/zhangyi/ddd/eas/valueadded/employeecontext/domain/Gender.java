package xyz.zhangyi.ddd.eas.valueadded.employeecontext.domain;

public enum Gender {
    Male, Female;

    public boolean isMale() {
        return this == Male;
    }

    public boolean isFemale() {
        return this == Female;
    }
}