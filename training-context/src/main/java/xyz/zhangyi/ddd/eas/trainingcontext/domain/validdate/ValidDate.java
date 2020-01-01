package xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate;

import java.time.LocalDateTime;

public class ValidDate {
    private LocalDateTime deadline;
    private ValidDateType validDateType;

    public ValidDate(LocalDateTime deadline, ValidDateType validDateType) {
        this.deadline = deadline;
        this.validDateType = validDateType;
    }

    public LocalDateTime deadline() {
        return this.deadline;
    }
}