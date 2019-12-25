package xyz.zhangyi.ddd.eas.employeecontext;

public enum Gender {
    Male, Female;

    public boolean isMale() {
        return this == Male;
    }

    public boolean isFemale() {
        return this == Female;
    }
}