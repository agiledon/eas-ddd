package xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

import java.time.LocalDateTime;

public class ValidDate {
    private TrainingId trainingId;
    private LocalDateTime deadline;
    private ValidDateType validDateType;

    public ValidDate(TrainingId trainingId, LocalDateTime deadline, ValidDateType validDateType) {
        this.deadline = deadline;
        this.validDateType = validDateType;
    }

    public LocalDateTime deadline() {
        return this.deadline;
    }
}