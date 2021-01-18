package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.acl.port.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.MailTemplate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.TemplateType;

import java.util.Optional;

@Mapper
@Repository
public interface MailTemplateRepository {
    Optional<MailTemplate> templateOf(TemplateType templateType);
}
