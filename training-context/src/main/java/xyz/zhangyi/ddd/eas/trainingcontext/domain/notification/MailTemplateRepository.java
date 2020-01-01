package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import java.util.Optional;

public interface MailTemplateRepository {
    Optional<MailTemplate> templateOf(TemplateType templateType);
}
