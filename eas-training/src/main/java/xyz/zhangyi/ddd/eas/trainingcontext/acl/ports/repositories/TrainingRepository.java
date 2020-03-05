package xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

import java.util.Optional;

@Mapper
@Repository
public interface TrainingRepository {
    Optional<Training> trainingOf(TrainingId trainingId);
    void add(Training training);
    void remove(Training training);
}