package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.south.port.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validate.ValidDate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.validate.ValidDateType;

import java.util.Optional;

@Mapper
@Repository
@Port(PortType.Repository)
public interface ValidDateRepository {
    Optional<ValidDate> validDateOf(TrainingId trainingId, ValidDateType validDateType);
    void add(ValidDate validDate);
    void remove(ValidDate validDate);
}
