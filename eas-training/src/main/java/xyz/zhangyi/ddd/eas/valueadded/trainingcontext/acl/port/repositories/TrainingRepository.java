package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;

import java.util.Optional;

@Mapper
@Repository
public interface TrainingRepository {
    Optional<Training> trainingOf(TrainingId trainingId);
    void add(Training training);
    void remove(Training training);
}