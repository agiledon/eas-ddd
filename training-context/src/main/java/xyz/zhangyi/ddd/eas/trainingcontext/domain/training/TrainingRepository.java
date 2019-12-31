package xyz.zhangyi.ddd.eas.trainingcontext.domain.training;

import java.util.Optional;

public interface TrainingRepository {
    Optional<Training> trainingOf(TrainingId trainingId);
}