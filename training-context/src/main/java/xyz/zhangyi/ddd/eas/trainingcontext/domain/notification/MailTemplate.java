package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

import java.util.ArrayList;

public class MailTemplate {
    private NotificationComposer notificationComposer;
    private String template;
    private TemplateType templateType;

    public MailTemplate(String template, TemplateType templateType) {
        this.templateType = templateType;
        this.template = template;
    }

    public Notification compose(VariableContext context) {
        notificationComposer = new NotificationComposer(template, context);
        return notificationComposer.compose();
    }
}