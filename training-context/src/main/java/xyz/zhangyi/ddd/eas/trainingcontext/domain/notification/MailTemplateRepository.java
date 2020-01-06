package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Mapper
@Repository
public interface MailTemplateRepository {
    Optional<MailTemplate> templateOf(TemplateType templateType);
}
