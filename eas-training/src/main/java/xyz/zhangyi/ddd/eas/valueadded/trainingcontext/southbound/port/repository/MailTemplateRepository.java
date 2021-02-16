package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.southbound.port.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.MailTemplate;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification.TemplateType;

import java.util.Optional;

@Mapper
@Repository
@Port(PortType.Repository)
public interface MailTemplateRepository {
    Optional<MailTemplate> templateOf(TemplateType templateType);
}
