package xyz.zhangyi.ddd.eas.trainingcontext.domain.training;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepository {
    Optional<Training> trainingOf(TrainingId trainingId);
    void add(Training training);
    void remove(Training training);
}