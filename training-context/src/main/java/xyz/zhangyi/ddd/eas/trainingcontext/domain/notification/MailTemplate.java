package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import java.util.UUID;

public class MailTemplate {
    private String id;
    private String template;
    private TemplateType templateType;

    public MailTemplate(String template, TemplateType templateType) {
        this.id = UUID.randomUUID().toString();
        this.templateType = templateType;
        this.template = template;
    }

    public Notification compose(VariableContext context) {
        NotificationComposer notificationComposer = this.templateType.composer(template, context);
        return notificationComposer.compose();
    }
}