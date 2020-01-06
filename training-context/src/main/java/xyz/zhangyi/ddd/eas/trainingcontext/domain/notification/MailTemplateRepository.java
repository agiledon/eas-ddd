package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailTemplateRepository {
    Optional<MailTemplate> templateOf(TemplateType templateType);
}
