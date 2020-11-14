package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.notification;

import org.stringtemplate.v4.ST;

import java.util.List;

public abstract class NotificationComposer {
    private static final char BEGIN_VARIABLE = '$';
    private static final char END_VARIABLE = '$';
    protected String template;

    public NotificationComposer(String template, VariableContext context) {
        this.template = template;
        setup(context);
    }

    protected void setup(VariableContext context) {}

    public Notification compose() {
        String from = renderFrom();
        String to = renderTo();
        String subject = renderSubject();
        String body = renderBody();
        return new Notification(from, to, subject, body);
    }

    protected abstract String renderFrom();

    protected abstract String renderSubject();

    protected abstract String renderTo();

    private String renderBody() {
        List<TemplateVariable> variables = registerVariables();
        ST st = new ST(template, BEGIN_VARIABLE, END_VARIABLE);
        for (TemplateVariable variable : variables) {
            st.add(variable.name(), variable.value());
        }
        return st.render();
    }

    protected abstract List<TemplateVariable> registerVariables();
}