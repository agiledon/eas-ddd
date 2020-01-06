package xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate;

import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

import java.util.Optional;

@Repository
public interface ValidDateRepository {
    Optional<ValidDate> validDateOf(TrainingId trainingId, ValidDateType validDateType);
    void add(ValidDate validDate);
    void remove(ValidDate validDate);
}
