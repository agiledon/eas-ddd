package xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

import java.util.Optional;

public interface ValidDateRepository {
    Optional<ValidDate> validDateOf(TrainingId trainingId, ValidDateType validDateType);
}
