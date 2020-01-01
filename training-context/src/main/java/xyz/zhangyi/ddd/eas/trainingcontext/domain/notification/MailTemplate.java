package xyz.zhangyi.ddd.eas.trainingcontext.domain.notification;

public class MailTemplate {
    private String template;
    private TemplateType templateType;

    public MailTemplate(String template, TemplateType templateType) {
        this.templateType = templateType;
        this.template = template;
    }

    public Notification compose(VariableContext context) {
        NotificationComposer notificationComposer = this.templateType.composer(template, context);
        return notificationComposer.compose();
    }
}