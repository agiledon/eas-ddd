package xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate.ValidDateType;

import java.util.Optional;

@Mapper
@Repository
public interface ValidDateRepository {
    Optional<ValidDate> validDateOf(TrainingId trainingId, ValidDateType validDateType);
    void add(ValidDate validDate);
    void remove(ValidDate validDate);
}
