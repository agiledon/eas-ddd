package xyz.zhangyi.ddd.eas.trainingcontext.domain.validdate;

import java.util.Optional;

public interface ValidDateRepository {
    Optional<ValidDate> validDateOf(String trainingId, ValidDateType validDateType);
}
