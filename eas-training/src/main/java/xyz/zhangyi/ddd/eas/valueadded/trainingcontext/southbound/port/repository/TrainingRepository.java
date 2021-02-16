package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.southbound.port.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;

import java.util.Optional;

@Mapper
@Repository
@Port(PortType.Repository)
public interface TrainingRepository {
    Optional<Training> trainingOf(TrainingId trainingId);
    void add(Training training);
    void remove(Training training);
}