package xyz.zhangyi.ddd.eas.trainingcontext.domain.training;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface TrainingRepository {
    Optional<Training> trainingOf(TrainingId trainingId);
}